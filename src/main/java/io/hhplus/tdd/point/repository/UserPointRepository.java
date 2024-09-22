package io.hhplus.tdd.point.repository;
import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;

import java.util.List;

public interface UserPointRepository {
    public UserPoint getUserPointById(long id);
    public List<PointHistory> getUserPointHistoryById(long id);
    public PointHistory insertUsePointById(long id, long amount);
    public PointHistory insertChargePointById(long id, long amount);
}
