package io.hhplus.tdd.Integration.point;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.hhplus.tdd.Integration.BaseIntegrationTest;
import io.hhplus.tdd.point.domain.entity.Point;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import io.hhplus.tdd.point.infrastructure.PointHistoryRepository;
import io.hhplus.tdd.point.infrastructure.UserPointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class pointService extends BaseIntegrationTest {
    @Autowired
    private UserPointRepository userPointRepository;

    @Autowired
    private PointHistoryRepository pointHistoryRepository;


    @Test
    public void 포인트_조회_테스트_성공() throws Exception {
        // GIVEN
        long id = 1;


        // WHEN
        ResultActions resultActions = mvc.perform(
                        get("/point/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON));

        // THEN
        System.out.println("hello");
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());

        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                ;
    }


    @Test
    public void 포인트_충전_테스트_성공() throws Exception {
        // GIVEN
        long id = 1;
        long amount = 10000L;

        Map<String, Long> requestBody = new HashMap<String, Long>();

        requestBody.put("amount", amount);


        String content = this.objectMapper.writeValueAsString(requestBody);



        // WHEN
        ResultActions resultActions = mvc.perform(
                patch("/point/" + id + "/" + "charge")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );



        // THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.point").value(amount))
        ;

        Point point = this.userPointRepository.findOneById(id);
        assertEquals(point.getPoint(), amount);
        // THEN

    }

    @Test
    public void 포인트_사용_테스트_성공() throws Exception {
        // GIVEN
        long id = 1;
        long defaultPoint = 10000L;

        this.userPointRepository.save(new Point(new UserPoint(id, defaultPoint, 0)));


        long amount = 10000L;

        Map<String, Long> requestBody = new HashMap<String, Long>();

        requestBody.put("amount", amount);

        String content = this.objectMapper.writeValueAsString(requestBody);



        // WHEN
        ResultActions resultActions = mvc.perform(
                patch("/point/"+ id+ "/use")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.point").value(defaultPoint - amount))
        ;


    }

    @Test
    public void 포인트_내역_조회_테스트() throws Exception {
        // GIVEN
        long id = 1;
        long chargePoint = 10000L;
        long usePoint = 10000L;

        Map<String, Long> chargeRequestBody = new HashMap<String, Long>();

        chargeRequestBody.put("amount", chargePoint);

        String chargeContent = this.objectMapper.writeValueAsString(chargeRequestBody);


        Map<String, Long> useRequestBody = new HashMap<String, Long>();

        useRequestBody.put("amount", usePoint);

        String useContent = this.objectMapper.writeValueAsString(useRequestBody);



        mvc.perform(
                patch("/point/"+ id + "/charge")
                        .content(chargeContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        mvc.perform(
                patch("/point/"+ id + "/use")
                        .content(useContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // WHEN
        ResultActions resultActions = mvc.perform(
                get("/point/" + id + "/histories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        ;


    }

    @Test
    public void 동시_충전_성공() throws Exception {
        // GIVEN
        int threadCount = 10;

        long id = 10;
        long amount = 1L;


        // WHEN
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);


        Map<String, Long> requestBody = new HashMap<String, Long>();

        requestBody.put("amount", amount);

        List<CompletableFuture> futures = new ArrayList<>(threadCount);

        for(int i = 0; i < threadCount; i++){
            // 비동기 요청을 위한
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {

                    try {
                        ResultActions resultActions = mvc.perform(
                                patch("/point/" + id + "/charge")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(requestBody))
                        );

                        resultActions
                                .andExpect(status().isOk());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
            }, executorService);

            futures.add(completableFuture);
        }

        for(CompletableFuture future : futures){
            future.join();
        }


        // THEN
        Point point = this.userPointRepository.findOneById(id);

        System.out.println(point.getUserId());
        System.out.println(point.getPoint());

        assertEquals(amount * threadCount, point.getPoint());
    }


    @Test
    public void 동시_사용_성공() throws Exception {
        // GIVEN
        int threadCount = 50;

        long id = 2;
        long amount = 1L;

        this.userPointRepository.save(new Point(new UserPoint(id, amount * threadCount, 0)));


        // WHEN
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);


        Map<String, Long> requestBody = new HashMap<String, Long>();

        requestBody.put("amount", amount);

        List<CompletableFuture> futures = new ArrayList<>(threadCount);

        for(int i = 0; i < threadCount; i++){

            // 비동기 요청을 위한
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {

                try {

                    ResultActions resultActions = mvc.perform(
                            patch("/point/" + id + "/use")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(requestBody))
                    );

                    resultActions
                            .andExpect(status().isOk());



                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, executorService);


            futures.add(completableFuture);
        }

        for(CompletableFuture future : futures){
            future.join();
        }


        // THEN
        Point point = this.userPointRepository.findOneById(id);

        System.out.println(point.getUserId());
        System.out.println(point.getPoint());

        assertEquals(0, point.getPoint());
    }


    @Test
    public void 동시_충전_사용_조회_성공() throws Exception {
        // GIVEN
        int threadCount = 20;

        long useId = 10;
        long chargeId = 100;
        long getId = 101;
        long amount = 1L;

        this.userPointRepository.save(new Point(new UserPoint(useId, amount * threadCount, 0)));


        // WHEN
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);


        Map<String, Long> requestBody = new HashMap<String, Long>();

        requestBody.put("amount", amount);

        List<CompletableFuture> futures = new ArrayList<>(threadCount);

        for(int i = 0; i < threadCount; i++){
            // 비동기 요청을 위한
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {

                try {

                    ResultActions chargeResultActions = mvc.perform(
                            patch("/point/" + chargeId + "/charge")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(requestBody))
                    );

                    chargeResultActions
                            .andExpect(status().isOk());



                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, executorService);

            futures.add(completableFuture);

            completableFuture = CompletableFuture.runAsync(() -> {

                try {


                    ResultActions useResultActions = mvc.perform(
                            patch("/point/" + useId + "/use")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(requestBody))
                    );

                    useResultActions
                            .andExpect(status().isOk());



                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, executorService);


            futures.add(completableFuture);
            completableFuture = CompletableFuture.runAsync(() -> {

                try {


                    ResultActions getResultActions = mvc.perform(
                            get("/point/" + getId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)

                    );

                    getResultActions
                            .andExpect(status().isOk());


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, executorService);


            futures.add(completableFuture);

        }

        sleep(5000);

        for(CompletableFuture future : futures){
            future.join();
        }


        // THEN
        Point chargePoint = this.userPointRepository.findOneById(chargeId);
        assertEquals(amount * threadCount, chargePoint.getPoint());

        Point usePoint = this.userPointRepository.findOneById(useId);
        assertEquals(0, usePoint.getPoint());

        Point getPoint = this.userPointRepository.findOneById(getId);
        assertEquals(0, getPoint.getPoint());
    }




}
