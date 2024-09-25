package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.point.record.PointHistory;

import java.util.List;

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


}
