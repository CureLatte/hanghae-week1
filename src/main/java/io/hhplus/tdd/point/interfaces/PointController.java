package io.hhplus.tdd.point.interfaces;
import io.hhplus.tdd.point.domain.entity.Point;
import io.hhplus.tdd.point.domain.entity.PointLog;
import io.hhplus.tdd.point.domain.PointService;
import io.hhplus.tdd.point.domain.vo.PointHistory;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/point")
public class PointController {

    private static final Logger log = LoggerFactory.getLogger(PointController.class);

    final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }


    /**
     * TODO - 특정 유저의 포인트를 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}")
    public UserPoint point(
            @PathVariable long id
    ) {

        Point point =  this.pointService.getUserPointById(id);
        return point.toUserPoint();

    }

    /**
     * TODO - 특정 유저의 포인트 충전/이용 내역을 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}/histories")
    public List<PointHistory> history(
            @PathVariable long id
    ) {
        List<PointLog> pointLogs = this.pointService.getUserPointHistoryListById(id);

        return pointLogs.stream().map(pointLog -> pointLog.toPointHistory()).collect(Collectors.toList());
    }

    /**
     * TODO - 특정 유저의 포인트를 충전하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/charge")
    public UserPoint charge(
            @PathVariable long id,
            @RequestBody Map<String, Long> requestBody
    ) {
        Long amount = requestBody.get("amount");
        Point point = this.pointService.chargePointById(id, amount);

        return point.toUserPoint();
    }

    /**
     * TODO - 특정 유저의 포인트를 사용하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/use")
    public Point use(
            @PathVariable long id,
            @RequestBody Map<String, Long> requestBody
    ) {
        Long amount = requestBody.get("amount");

        return this.pointService.usePointById(id, amount);
    }
}
