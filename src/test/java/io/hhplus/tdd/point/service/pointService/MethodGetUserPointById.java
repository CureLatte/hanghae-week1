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

    @Test
    public void 포인트_조회_성공(){
        // GIVEN
        long id = 1;

        // WHEN
        when(this.userPointRepository.findById(id)).thenReturn(new UserPoint(id, 0, 0));
        UserPoint userPoint = this.pointService.getUserPointById(id);

        // THEN
        assertEquals(userPoint.point(), 0);
    }
}
