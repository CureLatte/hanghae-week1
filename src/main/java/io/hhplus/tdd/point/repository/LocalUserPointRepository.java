package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.record.UserPoint;

public class LocalUserPointRepository implements UserPointRepository {

    UserPointTable userPointTable;

    public LocalUserPointRepository(UserPointTable userPointTable) {
        this.userPointTable = userPointTable;
    }

    @Override
    public UserPoint findById(long id) {

        return this.userPointTable.selectById(id);
    }


}
