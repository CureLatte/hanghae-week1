package io.hhplus.tdd.unitTest.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.repository.LocalCacheUserPointRepository;
import org.junit.jupiter.api.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.fail;

public class LocalCacheUserPointRepositoryTest {

    LocalCacheUserPointRepository userPointRepository;
    UserPointTable userPointTable;
    PointHistoryTable pointHistoryTable;

    public LocalCacheUserPointRepositoryTest(){
        // test 환경을 위한 UserPointRepository 생성
        this.userPointTable = new UserPointTable();
        this.pointHistoryTable = new PointHistoryTable();

        this.userPointRepository = new LocalCacheUserPointRepository(userPointTable, pointHistoryTable);
    }

    @BeforeAll
    public static void beforeAll(){}

    @BeforeEach
    public void beforeEach(){

    }
    @AfterEach
    public void afterEach(){}

    @AfterAll
    public static void afterAll(){

    }


    // TEST CASE
    @Test
    public void test_charge_point_when_point_not_create(){
        // GIVEN
        long id = 1;
        long amount = 1000;


        try {
            // WHEN
            this.userPointRepository.insertChargePointById(id, amount);

        } catch(Exception e){

            return;
        }

        fail();

    }

    @Test
    public void test_insert_use_point_when_0_point(){
        // GIVEN
        long id = 1;
        long amount = 1000;

        // point 생성
        this.userPointRepository.getUserPointById(id);

        try {
            // WHEN
            this.userPointRepository.insertUsePointById(1, amount);

        } catch(Exception e){
            // THEN
            return;
        }
        fail();
    }

}
