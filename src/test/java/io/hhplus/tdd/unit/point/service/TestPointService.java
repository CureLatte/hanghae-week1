package io.hhplus.tdd.unit.point.service;

import io.hhplus.tdd.point.domain.PointService;
import io.hhplus.tdd.point.domain.entity.Point;
import io.hhplus.tdd.point.domain.repository.IPointHistoryRepository;
import io.hhplus.tdd.point.domain.repository.IUserPointRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class TestPointService {

    @Mock
    IUserPointRepository userPointRepository;
    @Mock
    IPointHistoryRepository pointHistoryRepository;


    PointService pointService;

    public TestPointService() {
        this.userPointRepository = mock(IUserPointRepository.class);
        this.pointHistoryRepository = mock(IPointHistoryRepository.class);
        this.pointService = new PointService(this.userPointRepository, this.pointHistoryRepository);

    }


    @Test
    public void 포인트_조회_테스트_성공(){
        // GIVEN
        long id = 1000;

        // WHEN
        this.pointService.getUserPointById(id);


        // THEN
        verify(this.userPointRepository, times(1)).findOneById(id);
    }

    @Test
    public void 포인트_내역_조회_성공(){
        // GIVEN
        long id = 1000;

        // WHEN
        this.pointService.getUserPointHistoryListById(id);

        // THEN
        verify(this.pointHistoryRepository, times(1)).findAllById(id);

    }

    @Test
    public void 포인트_충전_성공(){
        // GIVEN
        long id = 1000;
        long amount = 10000L;

        Point point = mock(Point.class);
        when(this.userPointRepository.findOneById(id)).thenReturn(point);

        // WHEN
        this.pointService.chargePointById(id, amount);


        // THEN
        verify(this.userPointRepository, times(1)).findOneById(id);
        verify(this.pointHistoryRepository, times(1)).createChargeHistory(id, amount);
        verify(this.userPointRepository, times(1)).save(point);

        verify(point, times(1)).charge(amount);

    }


    @Test
    public void 포인트_사용_성공(){
        // GIVEN
        long id = 1000;
        long amount = 10000L;

        Point point = mock(Point.class);
        when(this.userPointRepository.findOneById(id)).thenReturn(point);

        // WHEN
        this.pointService.usePointById(id, amount);


        // THEN
        verify(this.userPointRepository, times(1)).findOneById(id);
        verify(this.pointHistoryRepository, times(1)).createUseHistory(id, amount);
        verify(this.userPointRepository, times(1)).save(point);

        verify(point, times(1)).use(amount);
    }

}
