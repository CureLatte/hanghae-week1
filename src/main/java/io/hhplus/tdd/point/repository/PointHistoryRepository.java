package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.point.record.PointHistory;

import java.util.List;

public interface PointHistoryRepository {
    public List<PointHistory> findAllById(long id);
}
