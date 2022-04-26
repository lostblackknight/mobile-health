package io.github.lostblackknight.hospital.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import io.github.lostblackknight.hospital.client.SearchClient;
import io.github.lostblackknight.hospital.service.ScheduleService;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.ScheduleDateDTO;
import io.github.lostblackknight.model.entity.hospital.Schedule;
import io.github.lostblackknight.model.entity.search.ScheduleES;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final SearchClient searchClient;

    @GetMapping("/schedules/date/{hospitalCode}/{deptCode}/{pageNum}/{pageSize}")
    public CommonResult<?> getScheduleDatesByHospitalCodeAndDeptCode(@PathVariable String deptCode,
                                                                     @PathVariable String hospitalCode,
                                                                     @PathVariable Long pageNum,
                                                                     @PathVariable Long pageSize,
                                                                     @RequestParam String date) {
        PageDTO<ScheduleDateDTO> scheduleDateDTOS = scheduleService.getScheduleDatesByHospitalCodeAndDeptCode(hospitalCode, deptCode, pageNum, pageSize, date);
        return CommonResult.success(scheduleDateDTOS);
    }

    @GetMapping("/schedules/doctors/{hospitalCode}/{deptCode}")
    public CommonResult<?> getDoctorListByScheduleDate(@RequestParam String date,
                                                       @PathVariable String deptCode,
                                                       @PathVariable String hospitalCode) {
        List<Schedule> schedules = scheduleService.getDoctorListByScheduleDate(hospitalCode, deptCode, date);
        return CommonResult.success(schedules);
    }

    @GetMapping("/schedules/{id}")
    public CommonResult<?> getScheduleById(@PathVariable String id) {
        return CommonResult.success(scheduleService.getById(id));
    }

    @PostMapping("/schedules")
    public CommonResult<?> modifySchedule(@RequestBody Schedule schedule) {
        final boolean flag = scheduleService.updateById(schedule);
        final ScheduleES scheduleES = new ScheduleES();
        BeanUtil.copyProperties(schedule, scheduleES);
        searchClient.uploadSchedulesToES(scheduleES);
        return flag ? CommonResult.success("更新成功"): CommonResult.fail("更新失败");
    }
}
