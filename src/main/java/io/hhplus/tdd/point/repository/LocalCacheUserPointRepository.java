package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.type.TransactionType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    public PointHistory insertUsePointById(long id, long amount) {
        // 포인트 사용

        long updateMillis = new java.util.Date().getTime();

        PointHistory pointHistory = this.pointHistoryTable.insert(id, amount, TransactionType.USE, updateMillis);

        // point 조회
        UserPoint userPoint = this.userPointTable.selectById(id);

        this.userPointTable.insertOrUpdate(id, userPoint.point() - amount);

        return pointHistory;
    }

    @Override
    public PointHistory insertChargePointById(long id, long amount) {
        // 포인트 충전
        long updateMillis = new java.util.Date().getTime();

        // history 추가
        PointHistory pointHistory = this.pointHistoryTable.insert(id, amount, TransactionType.CHARGE, updateMillis);

        // point 조회
        UserPoint userPoint = this.userPointTable.selectById(id);

        System.out.println("userPoint = " + userPoint);

        this.userPointTable.insertOrUpdate(id, userPoint.point() + amount);

        return pointHistory;
    }
}
