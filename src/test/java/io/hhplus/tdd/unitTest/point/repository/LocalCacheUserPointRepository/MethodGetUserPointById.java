package io.hhplus.tdd.unitTest.point.repository.LocalCacheUserPointRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MethodGetUserPointById extends TestLocalCacheUserPointRepository {
    /**
     * 생성자 함수
     */
    public MethodGetUserPointById() {
        super();
    }

    @Test
    public void 잘못된_ID_입력시_에러(){
        // GIVEN
        long id = 0;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, ()->{
           this.userPointRepository.getUserPointById(id);
        });

        // THEN
        assertEquals(err.getMessage(), "잘못된 ID 입니다.");
    }

}
