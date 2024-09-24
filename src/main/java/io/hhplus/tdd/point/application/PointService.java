package io.hhplus.tdd.point.application;

import io.hhplus.tdd.point.domain.entity.PointHistory;
import io.hhplus.tdd.point.domain.entity.UserPoint;

import java.util.List;

public interface PointService {
    List<PointHistory> getUserPointHistoryListById(long id);

    UserPoint getUserPoint(long id);

    UserPoint chargePoint(long id, long amount);

    UserPoint usePoint(long id, long amount);
}
