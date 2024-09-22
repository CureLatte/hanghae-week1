package io.hhplus.tdd.point;

import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.repository.UserPointRepository;
import io.hhplus.tdd.point.type.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    UserPointRepository userPointRepository;
    // DI
    public PointService(UserPointRepository userPointRepository) {
        this.userPointRepository = userPointRepository;
    }

    public PointHistory getUserPointById(long id){
        // ID 별 포인트 조회
        return new PointHistory(1, 1, 1, TransactionType.USE, 0);
    }

    public UserPoint getUserPoint(long id){
        return this.userPointRepository.getUserPointById(id);
    }

    public UserPoint chargePoint(long id, long amount){
        PointHistory pointsHistory =  this.userPointRepository.insertChargePointById(id, amount);
        return this.userPointRepository.getUserPointById(id );
    }

    public UserPoint usePoint(long id, long amount){
        System.out.println("PointService on");
        this.userPointRepository.insertUsePointById(id, amount);

        return userPointRepository.getUserPointById(id);

    }

}
