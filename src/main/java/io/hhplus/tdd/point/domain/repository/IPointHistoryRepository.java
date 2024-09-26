package io.hhplus.tdd.point.domain.repository;

import io.hhplus.tdd.point.domain.entity.PointLog;

import java.util.List;

public interface IPointHistoryRepository {
    public List<PointLog> findAllById(long id);
    public PointLog createHistory(PointLog pointHistory);
}
