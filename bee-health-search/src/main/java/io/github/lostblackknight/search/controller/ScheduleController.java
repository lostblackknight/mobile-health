package io.github.lostblackknight.search.controller;

import io.github.lostblackknight.model.dto.DoctorDTO;
import io.github.lostblackknight.model.dto.ScheduleDateDTO;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.model.vo.ScheduleDateParam;
import io.github.lostblackknight.model.vo.ScheduleDoctorParam;
import io.github.lostblackknight.model.vo.ScheduleParam;
import io.github.lostblackknight.search.entity.ScheduleESModel;
import io.github.lostblackknight.search.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedule")
    public CommonResult<?> getSchedule(ScheduleParam param) {
        List<ScheduleESModel> model = scheduleService.searchSchedule(param);
        return CommonResult.success(model);
    }

    @GetMapping("/schedule/doctorList")
    public CommonResult<?> getDoctorList(ScheduleDoctorParam param) {
        List<DoctorDTO> doctors = scheduleService.getDoctorList(param);
        return CommonResult.success(doctors);
    }

    @GetMapping("/schedule/dateList")
    public CommonResult<?> getScheduleDateList(ScheduleDateParam param) {
        List<ScheduleDateDTO> model = scheduleService.getScheduleDateList(param);
        return CommonResult.success(model);
    }
}
