package io.hhplus.tdd.point.infrastructure;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.domain.repository.IUserPointRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserPointRepository implements IUserPointRepository {
    UserPointTable userPointTable;

    public UserPointRepository(UserPointTable userPointTable) {
        this.userPointTable = userPointTable;
    }


}
