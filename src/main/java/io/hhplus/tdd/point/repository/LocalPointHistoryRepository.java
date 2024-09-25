package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.type.TransactionType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalPointHistoryRepository implements PointHistoryRepository {
    PointHistoryTable pointHistoryTable;

    // 생성자 구현
    public LocalPointHistoryRepository(PointHistoryTable pointHistoryTable) {
        this.pointHistoryTable = pointHistoryTable;
    }

    @Override
    public List<PointHistory> findAllById(long id) {
        return this.pointHistoryTable.selectAllByUserId(id);
    }

    /** 사용 포인트 내역 생성
     * @param userId long
     * @param amount long
     * @return PointHistory
     */
    @Override
    public PointHistory createChargePoint(long userId, long amount) {
        long nowMils = System.currentTimeMillis();
        return this.pointHistoryTable.insert(userId, amount, TransactionType.CHARGE, nowMils);
    }

}
