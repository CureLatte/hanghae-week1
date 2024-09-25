package io.hhplus.tdd.point.service.pointService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MethodGetUserPointHistoryListById extends TestPointService{
    public MethodGetUserPointHistoryListById(){
        super();
    }


    @Test
    public void 음수인_ID_조회시_에러(){
        // GIVEN
        long id = -1;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, ()->{
            this.pointService.getUserPointHistoryListById(id);
        });

        // THEN
        assertEquals(err.getMessage(), this.pointService.ID_VALIDATOR_ERROR_MESSAGE);
    }
}
