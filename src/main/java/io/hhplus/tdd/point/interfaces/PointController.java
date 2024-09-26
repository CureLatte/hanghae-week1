package io.hhplus.tdd.point.interfaces;
import io.hhplus.tdd.point.domain.entity.Point;
import io.hhplus.tdd.point.domain.entity.PointLog;
import io.hhplus.tdd.point.domain.PointService;
import io.hhplus.tdd.point.domain.vo.UserPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return new UserPoint(point.getUserId(), point.getPoint(), point.getUpdateMillis());

    }

    /**
     * TODO - 특정 유저의 포인트 충전/이용 내역을 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}/histories")
    public List<PointLog> history(
            @PathVariable long id
    ) {
        return this.pointService.getUserPointHistoryListById(id);
    }

    /**
     * TODO - 특정 유저의 포인트를 충전하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/charge")
    public Point charge(
            @PathVariable long id,
            @RequestBody Map<String, Long> requestBody
    ) {
        Long amount = requestBody.get("amount");
        return this.pointService.chargePointById(id, amount);
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
