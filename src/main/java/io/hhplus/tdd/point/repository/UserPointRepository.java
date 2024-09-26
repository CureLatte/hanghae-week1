package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.point.domain.vo.UserPoint;

public interface UserPointRepository {
    // point 조회
    public UserPoint findById(long id);
    public UserPoint increasePoint(long id, long amount);
    public UserPoint decreasePoint(long id, long amount);
}
