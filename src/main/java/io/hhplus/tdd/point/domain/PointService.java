package io.hhplus.tdd.point.domain;

import io.hhplus.tdd.point.domain.entity.Point;
import io.hhplus.tdd.point.domain.entity.PointLog;
import io.hhplus.tdd.point.domain.repository.IPointHistoryRepository;
import io.hhplus.tdd.point.domain.repository.IUserPointRepository;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PointService {

    IPointHistoryRepository pointHistoryRepository;
    IUserPointRepository userPointRepository;

    public PointService(IUserPointRepository userPointRepository , IPointHistoryRepository pointHistoryRepository ) {
        this.pointHistoryRepository = pointHistoryRepository;
        this.userPointRepository = userPointRepository;
    }

    public Point getUserPointById(long id) {
        return this.userPointRepository.findOneById(id);
    }

    public List<PointLog> getUserPointHistoryListById(long id){

        return this.pointHistoryRepository.findAllById(id);
    }

    public Point chargePointById(long id, long amount){

        Point point = this.userPointRepository.findOneById(id);

        point.charge(amount);

        // point History 생성
        PointLog pointLog = this.pointHistoryRepository.createChargeHistory(id, amount);

        return this.userPointRepository.save(point);
    }


    public Point usePointById(long id, long amount){

        Point point = userPointRepository.findOneById(id);

        point.use(amount);

        // PointHistory
        PointLog pointLog  = this.pointHistoryRepository.createUseHistory(id, amount);


        return this.userPointRepository.save(point);
    }



}
