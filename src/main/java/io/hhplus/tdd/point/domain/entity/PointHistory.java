package io.hhplus.tdd.point.domain.entity;

import io.hhplus.tdd.point.domain.vo.TransactionType;

public class PointHistory {
    long id;
    long userId;
    TransactionType type;
    long updateMillis;


    public PointHistory(PointHistory pointHistory) {
        this.id = pointHistory.id;
        this.userId = pointHistory.userId;
        this.type = pointHistory.type;
        this.updateMillis = pointHistory.updateMillis;
    }


}
