package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;

import java.util.List;


public class PointServiceImpl implements PointService {

    final UserPointRepository userPointRepository;
    final PointHistoryRepository pointHistoryRepository;

    public final String ID_VALIDATOR_ERROR_MESSAGE = "잘못된 ID 입니다";
    public final String AMOUNT_VALIDATOR_ERROR_MESSAGE = "금액은 0원 이상이어야 합니다";
    public final String MAX_POINT_VALIDATOR_ERROR_MESSAGE = "보유 가능한 최대 포인트 금액은 1,000,000 포인트 입니다.";
    public final String MAX_CHARGE_POINT_VALIDATOR_ERROR_MESSAGE = "한번에 충전 가능한 최대 포인트느 100,000 포인트 입니다.";
    public final long MAX_POINT = 1000000;
    public final long MAX_CHARGE_POINT = 100000;

    /** 생성자 의존성 주입
     * @param userPointRepository UserPointRepository
     * @param pointHistoryRepository PointHistoryRepository
     */
    public PointServiceImpl(UserPointRepository userPointRepository, PointHistoryRepository pointHistoryRepository) {
        this.userPointRepository = userPointRepository;
        this.pointHistoryRepository = pointHistoryRepository;
    }


    /** 유저별 포인트 조회
     * @param id long
     * @return UserPoint
     */
    @Override
    public UserPoint getUserPointById(long id) {

        this.idValidator(id);

        return this.userPointRepository.findById(id);
    }

    /** 포인트 내역 조회
     * @param id long
     * @return PointHistory[]
     */
    @Override
    public List<PointHistory> getUserPointHistoryListById(long id) {
        this.idValidator(id);

        return this.pointHistoryRepository.findAllById(id);
    }

    /** 포인트 충전하기
     * @param userId long
     * @param amount long
     * @return UserPoint
     */
    @Override
    public UserPoint chargePointById(long userId, long amount) {

        this.idValidator(userId);
        this.amountValidator(amount);

        if(amount > this.MAX_CHARGE_POINT){
            // 최대 충전금액
            throw new IllegalArgumentException(this.MAX_CHARGE_POINT_VALIDATOR_ERROR_MESSAGE);
        }

        // 최대 잔고 에러
        UserPoint userPoint = this.userPointRepository.findById(userId);
        if(userPoint.point() + amount >= this.MAX_POINT) {
            // 최대 1,000,000 원
            throw new IllegalArgumentException(this.MAX_POINT_VALIDATOR_ERROR_MESSAGE);
        }

        // point 내역 생성
        this.pointHistoryRepository.createChargePoint(userId, amount);

        // point 추가
        return this.userPointRepository.increasePoint(userId, amount);
    }


    public void idValidator(long id){
        if(id <=0){
            throw new IllegalArgumentException(this.ID_VALIDATOR_ERROR_MESSAGE);
        }
    }

    public void amountValidator(long amount){
        if(amount <=0){
            throw new IllegalArgumentException(this.AMOUNT_VALIDATOR_ERROR_MESSAGE);
        }
    }
}
