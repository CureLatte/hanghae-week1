package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.point.record.UserPoint;

public interface UserPointRepository {

    // point 조회
    public UserPoint findById(long id);


}
