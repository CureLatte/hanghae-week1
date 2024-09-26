package io.hhplus.tdd.point.domain.vo;

public class PatchPointType {
    long id;
    TransactionType transactionType;
    long amount;

    public PatchPointType(long id, TransactionType transactionType, long amount) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
    }
}
