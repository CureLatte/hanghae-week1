package io.hhplus.tdd.unitTest.point.service.pointServiceImpl;

import io.hhplus.tdd.point.domain.repository.UserPointRepository;
import org.junit.jupiter.api.*;

public class TestPointService {

    UserPointRepository userPointRepository;

    public TestPointService(UserPointRepository userPointRepository) {
        this.userPointRepository = userPointRepository;
    }

    @BeforeAll
    public static void beforeAll(){}

    @BeforeEach
    public void beforeEach() {}

    @AfterEach
    public void afterEach() {}

    @AfterAll
    public static void afterAll(){}

}
