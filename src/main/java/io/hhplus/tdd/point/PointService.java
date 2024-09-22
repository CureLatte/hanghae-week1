package io.hhplus.tdd.point;

import io.hhplus.tdd.point.record.PointHistory;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    // DI
    public PointService() {

    }

    public PointHistory getUserPointById(long id){
        // ID 별 포인트 조회
        return new PointHistory(1, 1, 1, TransactionType.USE, 0);
    }

}
