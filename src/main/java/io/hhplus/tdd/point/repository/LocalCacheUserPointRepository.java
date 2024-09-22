package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalCacheUserPointRepository implements UserPointRepository {
    UserPointTable userPointTable;
    PointHistoryTable pointHistoryTable;

    public LocalCacheUserPointRepository(UserPointTable userPointTable, PointHistoryTable pointHistoryTable) {
        this.userPointTable = userPointTable;
        this.pointHistoryTable = pointHistoryTable;
    }

    @Override
    public UserPoint getUserPointById(long id) {
        // 유저 포인트 조회
        return this.userPointTable.selectById(id);
    }

    @Override
    public List<PointHistory> getUserPointHistoryById(long id) {
        // 포인트 내역 조회

        return this.pointHistoryTable.selectAllByUserId(id);
    }

    @Override
    public UserPoint useUserPointById(long id, long amount) {
        // 포인트 사용
        return null;
    }

    @Override
    public UserPoint chargeUserPointById(long id, long amount) {
        return null;
    }
}
