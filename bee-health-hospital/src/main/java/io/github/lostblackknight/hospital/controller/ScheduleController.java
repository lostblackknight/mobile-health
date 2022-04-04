package io.github.lostblackknight.hospital.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import io.github.lostblackknight.hospital.service.ScheduleService;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.ScheduleDateDTO;
import io.github.lostblackknight.model.entity.hospital.Schedule;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedules/date/{hospitalCode}/{deptCode}/{pageNum}/{pageSize}")
    public CommonResult<?> getScheduleDatesByHospitalCodeAndDeptCode(@PathVariable String deptCode,
                                                                     @PathVariable String hospitalCode,
                                                                     @PathVariable Long pageNum,
                                                                     @PathVariable Long pageSize) {
        PageDTO<ScheduleDateDTO> scheduleDateDTOS = scheduleService.getScheduleDatesByHospitalCodeAndDeptCode(hospitalCode, deptCode, pageNum, pageSize);
        return CommonResult.success(scheduleDateDTOS);
    }

    @GetMapping("/schedules/doctors/{hospitalCode}/{deptCode}")
    public CommonResult<?> getDoctorListByScheduleDate(@RequestParam String date,
                                                       @PathVariable String deptCode,
                                                       @PathVariable String hospitalCode) {
        final DateTime dateTime = DateUtil.parse(date, "yyyy-MM-dd");
        final Date jdkDate = dateTime.toJdkDate();
        List<Schedule> schedules = scheduleService.getDoctorListByScheduleDate(hospitalCode, deptCode, jdkDate);
        return CommonResult.success(schedules);
    }
}
