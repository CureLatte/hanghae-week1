package io.hhplus.tdd.unitTest.point.repository.localCacheUserPointRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MethodGetUserPointHistoryById extends TestLocalCacheUserPointRepository {
    public MethodGetUserPointHistoryById() {
        super();
    }

    @Test
    public void 잘못된_ID_인_경우_에러(){
        // GIVEN
        long id = -1;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, ()->{
            this.userPointRepository.getUserPointHistoryById(id);
        });

        // THEN
        assertEquals(err.getMessage(), this.userPointRepository.idValidateErrorMessage);
    }
}
