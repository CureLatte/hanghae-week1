package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.domain.vo.PointHistory;
import io.hhplus.tdd.point.domain.vo.UserPoint;

import java.util.List;

public interface PointService {
    public UserPoint getUserPointById(long id);
    public List<PointHistory> getUserPointHistoryListById(long id);

    public UserPoint chargePointById(long userId, long amount);
    public UserPoint usePointById(long userId, long amount);
}
