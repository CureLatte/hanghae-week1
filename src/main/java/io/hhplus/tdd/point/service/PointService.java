package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;

import java.util.List;

public interface PointService {
    List<PointHistory> getUserPointHistoryListById(long id);

    UserPoint getUserPoint(long id);

    UserPoint chargePoint(long id, long amount);

    UserPoint usePoint(long id, long amount);
}
