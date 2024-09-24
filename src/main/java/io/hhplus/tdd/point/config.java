package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.repository.LocalCacheUserPointRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;
import io.hhplus.tdd.point.service.PointService;
import io.hhplus.tdd.point.service.PointServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    public UserPointRepository userPointRepository(UserPointTable userPointTable, PointHistoryTable pointHistoryTable) {
        return new LocalCacheUserPointRepository(userPointTable, pointHistoryTable);
    }

    @Bean
    public PointService pointService(UserPointRepository userPointRepository) {
        return new PointServiceImpl(userPointRepository);
    }
}
