package io.github.lostblackknight.search.controller;

import io.github.lostblackknight.model.dto.DoctorDTO;
import io.github.lostblackknight.model.dto.ScheduleDateDTO;
import io.github.lostblackknight.model.vo.*;
import io.github.lostblackknight.search.entity.ScheduleESModel;
import io.github.lostblackknight.search.service.ScheduleService;
import io.github.lostblackknight.search.vo.DeptDoctorDTO;
import io.github.lostblackknight.search.vo.DoctorScheduleDTO;
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

    @GetMapping("/schedule/doctor/deptList")
    public CommonResult<?> getDeptListByDoctorCode(ScheduleDeptParam param) {
        List<DeptDoctorDTO> dtos = scheduleService.getDeptListByDoctorCode(param);
        return CommonResult.success(dtos);
    }

    @GetMapping("/schedule/doctor/schedule")
    public CommonResult<?> getDoctorSchedule(ScheduleParam param) {
        DoctorScheduleDTO dto = scheduleService.getDoctorSchedule(param);
        return CommonResult.success(dto);
    }
}
