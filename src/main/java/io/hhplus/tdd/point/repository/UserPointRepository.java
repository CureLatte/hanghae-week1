package io.hhplus.tdd.point.repository;
import io.hhplus.tdd.point.record.UserPoint;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserPointRepository {
    public UserPoint getUserPointById(long id);
    public UserPoint UseUserPointById(long id, long amount);
    public UserPoint ChargeUserPointById(long id, long amount);
}
