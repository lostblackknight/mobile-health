package io.github.lostblackknight.order.client;

import io.github.lostblackknight.model.entity.hospital.Schedule;
import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-hospital")
public interface HospitalClient {

    @GetMapping("/schedules/{id}")
    CommonResult<Schedule> getScheduleById(@PathVariable String id);

    @PostMapping("/schedules")
    CommonResult<?> modifySchedule(@RequestBody Schedule schedule);

}
