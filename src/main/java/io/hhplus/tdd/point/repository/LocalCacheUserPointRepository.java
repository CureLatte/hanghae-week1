package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.type.TransactionType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalCacheUserPointRepository implements UserPointRepository {
    final UserPointTable userPointTable;
    final PointHistoryTable pointHistoryTable;

    public final String idValidateErrorMessage = "잘못된 ID 입니다.";
    public final String amountValidateErrorMessage = "금액은 0원 보다 높아야합니다.";


    /** 생성자 함수
     * @param userPointTable UserPointTable
     * @param pointHistoryTable PointHistoryTable
     */
    public LocalCacheUserPointRepository(UserPointTable userPointTable, PointHistoryTable pointHistoryTable) {

        this.userPointTable = userPointTable;
        this.pointHistoryTable = pointHistoryTable;
    }



    /** 포인트 조회 By ID
     * @param id long
     * @return UserPoint
     */
    @Override
    public UserPoint getUserPointById(long id) {
        //
        this.idValidate(id);

        return this.userPointTable.selectById(id);
    }

    /** 포인트 내역 조회 By ID
     * @param id long
     * @return List<PointHistory>
     */
    @Override
    public List<PointHistory> getUserPointHistoryById(long id) {
        this.idValidate(id);

        // 포인트 내역 조회
        return this.pointHistoryTable.selectAllByUserId(id);
    }

    /** 포인트 추가
     * @param id long
     * @param amount long
     * @return PointHistory
     */
    @Override
    public PointHistory insertUsePointById(long id, long amount) {
        // id check
        this.idValidate(id);
        this.amountValidate(amount);

        long updateMillis = new java.util.Date().getTime();

        PointHistory pointHistory = this.pointHistoryTable.insert(id, amount, TransactionType.USE, updateMillis);


        // point 조회
        UserPoint userPoint = this.userPointTable.selectById(id);

        this.userPointTable.insertOrUpdate(id, userPoint.point() - amount);

        return pointHistory;
    }

    @Override
    public PointHistory insertChargePointById(long id, long amount) {
        this.idValidate(id);
        this.amountValidate(amount);


        // 포인트 충전
        long updateMillis = new java.util.Date().getTime();

        // history 추가
        PointHistory pointHistory = this.pointHistoryTable.insert(id, amount, TransactionType.CHARGE, updateMillis);

        // point 조회
        UserPoint userPoint = this.userPointTable.selectById(id);

        this.userPointTable.insertOrUpdate(id, userPoint.point() + amount);

        return pointHistory;
    }

    /** id validator
     * @param id long
     */
    private void idValidate(long id){
        if(id <=0){
            throw new IllegalArgumentException(this.idValidateErrorMessage);
        }
    }

    /** amount validator
     * @param amount long
     */
    private void amountValidate(long amount){
        if(amount <=0){
            throw new IllegalArgumentException(this.amountValidateErrorMessage);
        }
    }
}
