package io.hhplus.tdd.point.service.pointService;

import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.type.TransactionType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MethodChargePointById extends TestPointService {
    public MethodChargePointById() {
        super();
    }

    @Test
    public void ID_음수인_경우() {
        // GIVEN
        long userId = -999;
        long amount = 100;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.pointService.chargePointById(userId, amount);

        });

        // THEN
        assertEquals(err.getMessage(), this.pointService.ID_VALIDATOR_ERROR_MESSAGE);
    }

    @Test
    public void 금액이_음수인_경우() {
        // GIVEN
        long userId = 999;
        long amount = -100;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.pointService.chargePointById(userId, amount);

        });

        // THEN
        assertEquals(err.getMessage(), this.pointService.AMOUNT_VALIDATOR_ERROR_MESSAGE);
    }

    @Test
    public void 최대_충전_금액_이상_충전시_에러() {
        // GIVEN
        long userId = 1;
        long chargePoint = this.pointService.MAX_CHARGE_POINT + 1;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.pointService.chargePointById(userId, chargePoint);
        });


        // THEN
        assertEquals(err.getMessage(), this.pointService.MAX_CHARGE_POINT_VALIDATOR_ERROR_MESSAGE);
    }

    @Test
    public void 최대_잔고_이상_충전시_에러() {
        // GIVEN
        long userId = 1;
        long chargePoint = 1;

        when(this.userPointRepository.findById(userId)).thenReturn(new UserPoint(userId, this.pointService.MAX_POINT, 0));

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.pointService.chargePointById(userId, chargePoint);
        });

        // THEN
        assertEquals(err.getMessage(), this.pointService.MAX_POINT_VALIDATOR_ERROR_MESSAGE);
    }


}
