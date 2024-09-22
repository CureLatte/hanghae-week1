package io.hhplus.tdd.point.record;

import io.hhplus.tdd.point.type.TransactionType;

public record PointHistory(
        long id,
        long userId,
        long amount,
        TransactionType type,
        long updateMillis
) {
}
