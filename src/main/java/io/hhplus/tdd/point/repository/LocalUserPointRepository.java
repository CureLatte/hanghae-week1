package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.record.UserPoint;

public class LocalUserPointRepository implements UserPointRepository {

    UserPointTable userPointTable;

    public LocalUserPointRepository(UserPointTable userPointTable) {
        this.userPointTable = userPointTable;
    }

    @Override
    public UserPoint findById(long id) {

        return this.userPointTable.selectById(id);
    }

    /** 포인트 추가
     * @param id long
     * @param amount long
     * @return UserPoint
     */
    @Override
    public UserPoint increasePoint(long id, long amount) {
        // 현재 포인트 조회
        UserPoint userPoint = this.userPointTable.selectById(id);
        return this.userPointTable.insertOrUpdate(id, userPoint.point() + amount);
    }

    @Override
    public UserPoint decreasePoint(long id, long amount) {
        //  현재 포인트 조회
        UserPoint userPoint = this.userPointTable.selectById(id);
        return this.userPointTable.insertOrUpdate(id, userPoint.point() - amount);
    }


}
