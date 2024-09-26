package io.hhplus.tdd.unit.point.service;

import io.hhplus.tdd.point.domain.repository.IPointHistoryRepository;
import io.hhplus.tdd.point.domain.repository.IUserPointRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;


public class TestPointService {

    IUserPointRepository userPointRepository;
    IPointHistoryRepository pointHistoryRepository;

    public TestPointService() {
        this.userPointRepository = mock(IUserPointRepository.class);
        this.pointHistoryRepository = mock(IPointHistoryRepository.class);

    }

}
