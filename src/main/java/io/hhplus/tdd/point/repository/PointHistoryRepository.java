package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.point.domain.vo.PointHistory;

import java.util.List;

public interface PointHistoryRepository {
    public List<PointHistory> findAllById(long id);
    public PointHistory createChargePoint(long userId, long amount);
    public PointHistory createUsePoint(long userId, long amount);
}
