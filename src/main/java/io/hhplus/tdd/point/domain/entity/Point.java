package io.hhplus.tdd.point.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hhplus.tdd.point.domain.vo.BusinessError;
import io.hhplus.tdd.point.domain.vo.PointHistory;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import lombok.Getter;

import java.util.List;

@Getter
public class Point {
    long userId;
    long point;
    long updateMillis;

    final String AMOUNT_VALIDATOR_ERROR_MESSAGE = "금액은 0보다 작을 수 없습니다.";
    final String ID_VALIDATOR_ERROR_MESSAGE = "ID 는 0보다 작음ㄹ 수 없습니다.";

    final String CHARGE_MAX_POINT_ERROR_MESSAGE = "충전 가능한 최대 금액은 100,000 포인트 입니다.";
    final String OVER_BALANCE_ERROR_MESSAGE= "최대 보유 포인트 금액은 1,000,000 포인트입니다.";
    final String UNDER_BALANCE_ERROR_MESSAGE = "최소 보유 포인트 금액은 0 포인트 입니다.";

    final long MAX_CHARGE_POINT = 100000;
    final long BALANCE_MAX_POINT = 1000000;
    final long BALANCE_MIN_POINT = 0;

    public Point(UserPoint userPoint) {
        if(userPoint.id() <0){
            throw new BusinessError(ID_VALIDATOR_ERROR_MESSAGE);
        }

        this.userId = userPoint.id();
        this.point = userPoint.point();
        this.updateMillis = userPoint.updateMillis();
    }


    public void charge(long amount){
        if(amount <=0){
            throw new BusinessError(this.AMOUNT_VALIDATOR_ERROR_MESSAGE);
        }

        if(amount > this.MAX_CHARGE_POINT){
            throw new BusinessError(this.CHARGE_MAX_POINT_ERROR_MESSAGE);
        }

        if(this.point + amount > this.BALANCE_MAX_POINT){
            throw new BusinessError(this.OVER_BALANCE_ERROR_MESSAGE);
        }

        this.point += amount;
    }

    public void use(long amount){
        if(amount <=0){
            throw new BusinessError(this.AMOUNT_VALIDATOR_ERROR_MESSAGE);
        }

        if(this.point - amount < this.BALANCE_MIN_POINT){
            throw new BusinessError(this.UNDER_BALANCE_ERROR_MESSAGE);
        }


        this.point -= amount;
    }

    public UserPoint toUserPoint(){
        return new UserPoint(this.userId, this.point, this.updateMillis);
    }

}
