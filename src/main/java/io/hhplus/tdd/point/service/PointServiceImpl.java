package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.repository.UserPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    UserPointRepository userPointRepository;
    // DI
    public PointServiceImpl(UserPointRepository userPointRepository) {
        this.userPointRepository = userPointRepository;
    }

    @Override
    public List<PointHistory> getUserPointHistoryListById(long id){
        // ID 별 포인트 조회
        return this.userPointRepository.getUserPointHistoryById(id);
    }

    @Override
    public UserPoint getUserPoint(long id){
        return this.userPointRepository.getUserPointById(id);
    }

    @Override
    public UserPoint chargePoint(long id, long amount){
        PointHistory pointsHistory =  this.userPointRepository.insertChargePointById(id, amount);
        return this.userPointRepository.getUserPointById(id );
    }

    @Override
    public UserPoint usePoint(long id, long amount){

        // 남은 포인트 0원 일 때 Error
        UserPoint userPoint = this.userPointRepository.getUserPointById(id);
        if(userPoint.point() == 0){
            throw new IllegalArgumentException("잔액이 부족합니다");
        }

        this.userPointRepository.insertUsePointById(id, amount);

        return userPointRepository.getUserPointById(id);

    }

}
