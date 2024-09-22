package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.record.UserPoint;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class LocalCacheUserPointRepository implements UserPointRepository {
    UserPointTable userPointTable;

    public LocalCacheUserPointRepository(UserPointTable userPointTable) {
        this.userPointTable = userPointTable;
    }

    @Override
    public UserPoint getUserPointById(long id) {
        return this.userPointTable.selectById(id);
    }

    @Override
    public UserPoint UseUserPointById(long id, long amount) {
        return null;
    }

    @Override
    public UserPoint ChargeUserPointById(long id, long amount) {
        return null;
    }
}
