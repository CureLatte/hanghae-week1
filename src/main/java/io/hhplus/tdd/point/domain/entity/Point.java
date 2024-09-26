package io.hhplus.tdd.point.domain.entity;

import io.hhplus.tdd.point.domain.vo.PointHistory;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import lombok.Getter;

import java.util.List;

@Getter
public class Point {
    long userId;
    long point;

    public Point(UserPoint userPoint) {
        this.userId = userPoint.id();
        this.point = userPoint.point();
    }

}
