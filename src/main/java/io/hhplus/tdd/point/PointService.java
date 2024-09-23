package io.hhplus.tdd.point;

import io.hhplus.tdd.point.record.ErrorResponse;
import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.repository.UserPointRepository;
import io.hhplus.tdd.point.type.TransactionType;
import org.springframework.stereotype.Service;
import io.hhplus.tdd.point.type.ErrorType;

import java.util.List;

@Service
public class PointService {

    UserPointRepository userPointRepository;
    // DI
    public PointService(UserPointRepository userPointRepository) {
        this.userPointRepository = userPointRepository;
    }

    public List<PointHistory> getUserPointHistoryListById(long id){
        // ID 별 포인트 조회
        return this.userPointRepository.getUserPointHistoryById(id);
    }

    public UserPoint getUserPoint(long id){
        if(id == 1){
            ErrorResponse errorResponse = new ErrorResponse("400", "잔액이 부족합니다");
            throw new ErrorType(errorResponse);
        }

        return this.userPointRepository.getUserPointById(id);
    }

    public UserPoint chargePoint(long id, long amount){
        PointHistory pointsHistory =  this.userPointRepository.insertChargePointById(id, amount);
        return this.userPointRepository.getUserPointById(id );
    }

    public UserPoint usePoint(long id, long amount){

        // 남은 포인트 0원 일 때 Error
        UserPoint userPoint = this.userPointRepository.getUserPointById(id);
        if(userPoint.point() == 0){
            ErrorResponse errorResponse = new ErrorResponse("400", "잔액이 부족합니다");
            throw new RuntimeException(String.valueOf(errorResponse));
        }

        this.userPointRepository.insertUsePointById(id, amount);

        return userPointRepository.getUserPointById(id);

    }

}
