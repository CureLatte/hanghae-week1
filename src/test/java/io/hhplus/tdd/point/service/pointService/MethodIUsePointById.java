package io.hhplus.tdd.point.service.pointService;

import io.hhplus.tdd.point.record.UserPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MethodIUsePointById extends TestPointService{
    public MethodIUsePointById() {
        super();
    }

    @Test
    public void 음수인_ID_일_때_에러(){
        // GIVEN
        long userId = -999;
        long amount = 1000;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.pointService.usePointById(userId, amount);
        });

        // THEN
        assertEquals(err.getMessage(), this.pointService.ID_VALIDATOR_ERROR_MESSAGE);
    }

    @Test
    public void 음수_금액_일_때_에러(){
        // GIVEN
        long userId = 100;
        long amount = - 999;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.pointService.usePointById(userId, amount);
        });

        // THEN
        assertEquals(err.getMessage(), this.pointService.AMOUNT_VALIDATOR_ERROR_MESSAGE);
    }

    @Test
    public void 포인트가_사용시_남은_금액이_0원_일_때_에러(){
        // GIVEN
        long userId = 100;
        long amount = 1000;

        when(this.pointService.getUserPointById(userId)).thenReturn(new UserPoint(userId, 0, 0));

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.pointService.usePointById(userId, amount);
        });

        // THEN
        assertEquals(err.getMessage(), this.pointService.USE_MIN_POINT_VALIDATOR_ERROR_MESSAGE);
    }

    @Test
    public void 포인트가_사용시_남은_금액이_음수_일_때_에러(){
        // GIVEN
        long userId = 100;
        long amount = 1000;

        when(this.pointService.getUserPointById(userId)).thenReturn(new UserPoint(userId, 100, 0));

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.pointService.usePointById(userId, amount);
        });

        // THEN
        assertEquals(err.getMessage(), this.pointService.USE_MIN_POINT_VALIDATOR_ERROR_MESSAGE);
    }
}
