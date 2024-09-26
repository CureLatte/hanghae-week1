package io.hhplus.tdd.point.domain.repository;

import io.hhplus.tdd.point.domain.entity.Point;

public interface IUserPointRepository {
    public Point findOneById(long id);
    public Point save(Point point);
}
