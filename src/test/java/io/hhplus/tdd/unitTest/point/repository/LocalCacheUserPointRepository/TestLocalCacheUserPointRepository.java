package io.hhplus.tdd.unitTest.point.repository.LocalCacheUserPointRepository;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.repository.LocalCacheUserPointRepository;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestLocalCacheUserPointRepository {

    LocalCacheUserPointRepository userPointRepository;
    UserPointTable userPointTable;
    PointHistoryTable pointHistoryTable;

    public TestLocalCacheUserPointRepository(){
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


//    @Test
//    public void 사용시_잔고_0원_인_경우_에러(){
//        // go to Service
//        // GIVEN
//        long id = 1;
//        long amount = 0;
//
//        // point 생성
//        this.userPointRepository.getUserPointById(id);
//
//        try {
//            // WHEN
//            this.userPointRepository.insertUsePointById(1, amount);
//
//        } catch(Exception e){
//            // THEN
//            return;
//        }
//        fail();
//    }




}
