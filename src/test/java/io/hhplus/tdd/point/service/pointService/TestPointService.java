package io.hhplus.tdd.point.service.pointService;

import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;
import io.hhplus.tdd.point.service.PointServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;


public class TestPointService {
    UserPointRepository userPointRepository;
    PointHistoryRepository pointHistoryRepository;

    PointServiceImpl pointService;

    public TestPointService() {
        this.userPointRepository = mock(UserPointRepository.class);
        this.pointHistoryRepository = mock(PointHistoryRepository.class);
        this.pointService = new PointServiceImpl(this.userPointRepository, this.pointHistoryRepository);

    }

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {}

    @AfterEach
    public void afterEach() {}

    @AfterAll
    public static void afterAll() {}
}

