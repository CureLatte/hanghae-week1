package io.hhplus.tdd.unitTest.point.repository.localCacheUserPointRepository;

import io.hhplus.tdd.point.domain.entity.PointHistory;
import io.hhplus.tdd.point.domain.entity.UserPoint;
import io.hhplus.tdd.point.domain.vo.TransactionType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MethodInsertUsePointById extends TestLocalCacheUserPointRepository {
    public MethodInsertUsePointById() {
        super();
    }

    @Test
    public void 잘못된_ID_인_경우(){
        // GIVEN
        long id = -1;
        long amount = 1000;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.userPointRepository.insertUsePointById(id, amount);
        });

        // THEN
        assertEquals(err.getMessage(), this.userPointRepository.idValidateErrorMessage);

    }

    @Test
    public void 사용_포인트가_음수인_경우(){
        // GIVEN
        long id = 1;
        long amount = -1000;

        // WHEN
        Exception err = assertThrows(IllegalArgumentException.class, () -> {
            this.userPointRepository.insertUsePointById(id, amount);
        });

        // THEN
        assertEquals(err.getMessage(), this.userPointRepository.amountValidateErrorMessage);

    }


    @Test
    public void 사용_포인트가_0_원인_경우(){
        // GIVEN
        long id = 1;
        long amount = 0;

        // WHEN
        Exception exception  = assertThrows(IllegalArgumentException.class, () -> {
            this.userPointRepository.insertUsePointById(id, amount);
        });

        // THEN
        assertEquals(exception.getMessage(),"금액은 0원 보다 높아야합니다.");


    }

    @Test
    public void 사용시_point_내역_생성_확인(){
        // GIVEN
        long id = 989;
        long amount = 1000;


        // WHEN
        this.userPointRepository.insertUsePointById(id, amount);

        // THEN
        List<PointHistory> pointHistories = this.pointHistoryTable.selectAllByUserId(id);

        assertEquals(pointHistories.size(), 1);

        PointHistory pointHistory = pointHistories.get(0);
        assertEquals(pointHistory.amount(), amount);
        assertEquals(pointHistory.userId(), id);
        assertEquals(pointHistory.type(), TransactionType.USE);



    }

    @Test
    public void 사용시_point_감소_확인(){
        // GIVEN
        long id = 1;
        long amount = 1000;
        this.userPointTable.insertOrUpdate(id, amount);

        // WHEN
        this.userPointRepository.insertUsePointById(id, amount);


        // THEN

        UserPoint userPoint = this.userPointTable.selectById(id);

        assertEquals(userPoint.id(), id);
        assertEquals(userPoint.point(), 0);
    }


}
