package io.hhplus.tdd.point.domain;

import io.hhplus.tdd.point.domain.entity.Point;
import io.hhplus.tdd.point.domain.vo.BusinessError;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPoint {
    public TestPoint() {
    }

    @Test
    public void 포인트_충전_성공(){
        // GIVEN
        long id = 1;
        long defaultPoint = 0;
        long updateMillis = 0;

        long amount = 10000;

        UserPoint userPoint = new UserPoint(id, defaultPoint, updateMillis);
        Point point = new Point(userPoint);

        // WHEN
        point.charge(amount);

        // THEN
        assertEquals(point.getPoint(), defaultPoint + amount);

    }



    @Test
    public void 음수_금액_충전시_에러(){
        // GIVEN
        long id = 1;
        long defaultPoint = 0;
        long amount = -999;
        long updateMillis = 0;

        UserPoint userPoint = new UserPoint(id, defaultPoint, updateMillis);

        Point point = new Point(userPoint);

        // WHEN
        Exception err = assertThrows(BusinessError.class, ()->{
            point.charge(amount);
        });

        assertEquals(err.getMessage(), point.getAMOUNT_VALIDATOR_ERROR_MESSAGE());
    }

    @Test
    public void 최대_충전_금액_사용시_에러(){
        // GIVEN
        long id = 1;
        long defaultPoint = 0;
        long amount = 10000000;
        long updateMillis = 0;

        UserPoint userPoint = new UserPoint(id, defaultPoint, updateMillis);

        Point point = new Point(userPoint);

        // WHEN
        Exception err = assertThrows(BusinessError.class, ()->{
            point.charge(amount);
        });

        // THEN
        assertEquals(err.getMessage(), point.getCHARGE_MAX_POINT_ERROR_MESSAGE());
    }

    @Test
    public void 최대_잔고_금액_넘기면_에러(){
        // GIVEN

        Point dummyPoint = new Point(new UserPoint(0, 0, 0));

        long id = 1;
        long defaultPoint = dummyPoint.getBALANCE_MAX_POINT();
        long updateMillis = 0;

        UserPoint userPoint = new UserPoint(id, defaultPoint, updateMillis);
        Point point = new Point(userPoint);

        // WHEN
        Exception err = assertThrows(BusinessError.class, ()->{
            point.charge(1);
        });

        // THEN
        assertEquals(err.getMessage(), point.getOVER_BALANCE_ERROR_MESSAGE());
    }


    @Test
    public void 포인트_사용_성공(){
        // GIVEN
        long id = 1;
        long defaultPoint = 10000;
        long updateMillis = 0;
        long amount = 500;

        UserPoint userPoint = new UserPoint(id, defaultPoint, updateMillis);
        Point point = new Point(userPoint);


        // WHEN
        point.use(amount);

        // THEN
        assertEquals(point.getPoint(), defaultPoint - amount);
    }

    @Test
    public void 포인트_사용시_남은_금액_0원_일_때_에러(){
        // GIVEN
        long id = 1;
        long defaultPoint= 0;
        long updateMillis = 0;
        long amount = 10000;

        UserPoint userPoint = new UserPoint(id, defaultPoint, updateMillis);
        Point point = new Point(userPoint);

        // WHEN
        Exception err = assertThrows(BusinessError.class, ()->{
            point.use(amount);
        });


        // THEN
        assertEquals(err.getMessage(), point.getUNDER_BALANCE_ERROR_MESSAGE());

    }

    @Test
    public void 음수인_ID_일_때_에러(){
        // GIVEN
        Point dummyPoint = new Point(new UserPoint(0, 0, 0));

        long id = -1;
        long defaultPoint= 0;
        long updateMillis = 0;

        UserPoint userPoint = new UserPoint(id, defaultPoint, updateMillis);


        // WHEN
        Exception err = assertThrows(BusinessError.class, ()->{
            Point point = new Point(userPoint);
        });


        // THEN
        assertEquals(err.getMessage(), dummyPoint.getID_VALIDATOR_ERROR_MESSAGE());

    }

    @Test
    public void 포인트_조회_성공(){
        // GIVEN
        long id = 100;
        long amount= 10000;
        long updateMillis = 0;

        UserPoint userPoint = new UserPoint(id, amount, updateMillis);

        // WHEN
        Point point = new Point(userPoint);

        // THEN
        assertEquals(point.getPoint(), amount);
        assertEquals(point.getUserId(), id);
    }


}
