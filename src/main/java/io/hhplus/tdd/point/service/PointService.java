package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;

import java.util.List;

public interface PointService {
    public UserPoint getUserPointById(long id);
    public List<PointHistory> getUserPointHistoryListById(long id);
}
