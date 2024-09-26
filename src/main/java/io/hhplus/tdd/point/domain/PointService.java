package io.hhplus.tdd.point.domain;

import io.hhplus.tdd.point.domain.entity.Point;
import io.hhplus.tdd.point.domain.entity.PointLog;
import io.hhplus.tdd.point.domain.repository.IPointHistoryRepository;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PointService {

    IPointHistoryRepository pointHistoryRepository;
    IPointHistoryRepository userPointRepository;

    public PointService(IPointHistoryRepository pointHistoryRepository, IPointHistoryRepository userPointRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
        this.userPointRepository = userPointRepository;
    }

    public Point getUserPointById(long id) {

        return new Point(new UserPoint(0, 0, 0));
    }

    public List<PointLog> getUserPointHistoryListById(long id){
        return null;
    }

    public Point chargePointById(long id, long amount){
        return new Point(new UserPoint(0, 0, 0));
    }


    public Point usePointById(long id, long amount){
        return new Point(new UserPoint(0, 0, 0));
    }



}
