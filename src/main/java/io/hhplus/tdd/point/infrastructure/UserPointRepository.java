package io.hhplus.tdd.point.infrastructure;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.domain.entity.Point;
import io.hhplus.tdd.point.domain.repository.IUserPointRepository;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import org.springframework.stereotype.Repository;

@Repository
public class UserPointRepository implements IUserPointRepository {
    UserPointTable userPointTable;

    public UserPointRepository(UserPointTable userPointTable) {
        this.userPointTable = userPointTable;
    }


    @Override
    public Point findOneById(long id) {
        UserPoint userPoint = this.userPointTable.selectById(id);
        return new Point(userPoint);
    }

    @Override
    public Point save(Point point) {
        UserPoint userPoint = this.userPointTable.insertOrUpdate(point.getUserId(), point.getPoint());
        return new Point(userPoint);
    }
}
