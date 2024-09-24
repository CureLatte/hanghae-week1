package io.hhplus.tdd.point.domain.repository;
import io.hhplus.tdd.point.domain.entity.PointHistory;
import io.hhplus.tdd.point.domain.entity.UserPoint;

import java.util.List;

public interface UserPointRepository {
    public UserPoint getUserPointById(long id);
    public List<PointHistory> getUserPointHistoryById(long id);
    public PointHistory insertUsePointById(long id, long amount);
    public PointHistory insertChargePointById(long id, long amount);
}
