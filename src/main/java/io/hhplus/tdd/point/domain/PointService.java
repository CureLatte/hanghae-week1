package io.hhplus.tdd.point.domain;

import io.hhplus.tdd.point.domain.entity.Point;
import io.hhplus.tdd.point.domain.entity.PointLog;
import io.hhplus.tdd.point.domain.repository.IPointHistoryRepository;
import io.hhplus.tdd.point.domain.repository.IUserPointRepository;
import io.hhplus.tdd.point.domain.vo.BusinessError;
import io.hhplus.tdd.point.domain.vo.PatchPointType;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class PointService {

    IPointHistoryRepository pointHistoryRepository;
    IUserPointRepository userPointRepository;


    final ConcurrentHashMap <Long, Lock> patchPointTypeMap = new ConcurrentHashMap <Long, Lock>();

    public PointService(IUserPointRepository userPointRepository , IPointHistoryRepository pointHistoryRepository ) {
        this.pointHistoryRepository = pointHistoryRepository;
        this.userPointRepository = userPointRepository;
    }

    /** 포인트 조회
     * @param id long
     * @return Point
     */
    public Point getUserPointById(long id) {
        System.out.println("GET POINT START");
        return this.userPointRepository.findOneById(id);
    }

    /**
     * @param id long
     * @return List<PointLog>
     */
    public List<PointLog> getUserPointHistoryListById(long id){
        return this.pointHistoryRepository.findAllById(id);
    }

    public void Lock(long id){


    }

    public Point chargePointById(long id, long amount){

        System.out.println("CHARGE POINT START");
        Lock lock = patchPointTypeMap.computeIfAbsent(id, k -> new ReentrantLock(true));
        lock.lock();

        try {
            Point point = this.userPointRepository.findOneById(id);

            point.charge(amount);

            // point History 생성
            PointLog pointLog = this.pointHistoryRepository.createChargeHistory(id, amount);


            Point updatePoint =  this.userPointRepository.save(point);

            return updatePoint;

        } finally {
            lock.unlock();

        }


    }


    /**
     * @param id long
     * @param amount long
     * @return Point
     */
    public Point usePointById(long id, long amount){
        System.out.println("USE POINT START");
        Lock lock = patchPointTypeMap.computeIfAbsent(id, k -> new ReentrantLock(true));
        lock.lock();

        try {


            Point point = userPointRepository.findOneById(id);

            point.use(amount);

            // PointHistory
            PointLog pointLog  = this.pointHistoryRepository.createUseHistory(id, amount);

            Point newPoint =  this.userPointRepository.save(point);

            return newPoint;

        } finally {
            lock.unlock();
        }

    }



}
