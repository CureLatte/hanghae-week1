package io.hhplus.tdd.point.domain.entity;

import io.hhplus.tdd.point.domain.vo.PointHistory;
import io.hhplus.tdd.point.domain.vo.TransactionType;
import lombok.Getter;

@Getter
public class PointLog {
    long id;
    long userId;
    TransactionType type;
    long updateMillis;
    long amount;

    public PointLog(PointHistory pointHistory) {
        this.id = pointHistory.id();
        this.userId = pointHistory.userId();
        this.type = pointHistory.type();
        this.updateMillis = pointHistory.updateMillis();
        this.amount = pointHistory.amount();

    }



}
