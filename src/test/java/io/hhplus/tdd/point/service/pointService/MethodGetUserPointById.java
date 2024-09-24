package io.hhplus.tdd.point.service.pointService;

import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.service.PointService;
import io.hhplus.tdd.point.service.PointServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MethodGetUserPointById extends TestPointService {

    public MethodGetUserPointById() {
        super();
    }


    @Test
    public void 잘못된_ID_인경우(){
        // GIVEN
        long id = 0;

        // WHEN
        Exception err =  assertThrows(IllegalArgumentException.class, ()->{
            this.pointService.getUserPointById(id);
        }) ;

        // THEN
        assertEquals(err.getMessage(), this.pointService.ID_VALIDATOR_ERROR_MESSAGE);

    }
}
