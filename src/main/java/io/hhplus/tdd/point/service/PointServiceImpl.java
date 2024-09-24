package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.repository.UserPointRepository;

public class PointServiceImpl implements PointService {

    final UserPointRepository userPointRepository;

    final String ID_VALIDATOR_ERROR_MESSAGE = "잘못된 ID 입니다";
    final String AMOUNT_VALIDATOR_ERROR_MESSAGE = "금액은 0원 이상이어야 합니다";


    public PointServiceImpl(UserPointRepository userPointRepository) {
        this.userPointRepository = userPointRepository;
    }


    /** 유저별 포인트 조회
     * @param id long
     * @return UserPoint
     */
    @Override
    public UserPoint getUserPointById(long id) {

        this.idValidator(id);

        UserPoint userPoint = this.userPointRepository.findById(id);

        return this.userPointRepository.findById(id);
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
