package io.hhplus.tdd.point.infrastructure;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.point.domain.entity.PointLog;
import io.hhplus.tdd.point.domain.repository.IPointHistoryRepository;
import io.hhplus.tdd.point.domain.vo.PointHistory;
import io.hhplus.tdd.point.domain.vo.TransactionType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PointHistoryRepository implements IPointHistoryRepository {
    PointHistoryTable pointHistoryTable;

    public PointHistoryRepository(PointHistoryTable pointHistoryTable) {
        this.pointHistoryTable = pointHistoryTable;
    }


    @Override
    public List<PointLog> findAllById(long id) {
        List<PointHistory> pointHistories = this.pointHistoryTable.selectAllByUserId(id);
        return pointHistories.stream().map(pointHistory -> new PointLog(pointHistory)).collect(Collectors.toList());
    }

    @Override
    public PointLog createChargeHistory(long id, long amount) {
        long now = System.currentTimeMillis();
        PointHistory pointHistory = this.pointHistoryTable.insert(id, amount, TransactionType.CHARGE, now);
        return new PointLog(pointHistory);
    }

    @Override
    public PointLog createUseHistory(long id, long amount) {
        long now = System.currentTimeMillis();
        PointHistory pointHistory = this.pointHistoryTable.insert(id, amount, TransactionType.USE, now);
        return new PointLog(pointHistory);
    }
}
