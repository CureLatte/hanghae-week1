package io.hhplus.tdd.point.infrastructure;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.point.domain.repository.IPointHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PointHistoryRepository implements IPointHistoryRepository {
    PointHistoryTable pointHistoryTable;

    public PointHistoryRepository(PointHistoryTable pointHistoryTable) {
        this.pointHistoryTable = pointHistoryTable;
    }



}
