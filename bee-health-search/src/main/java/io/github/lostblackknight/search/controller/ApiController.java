package io.github.lostblackknight.search.controller;

import cn.hutool.core.bean.BeanUtil;
import io.github.lostblackknight.model.entity.search.DeptES;
import io.github.lostblackknight.model.entity.search.HospitalES;
import io.github.lostblackknight.model.entity.search.ScheduleES;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.search.entity.DeptESModel;
import io.github.lostblackknight.search.entity.HospitalESModel;
import io.github.lostblackknight.search.entity.ScheduleESModel;
import io.github.lostblackknight.search.service.DeptService;
import io.github.lostblackknight.search.service.HospitalService;
import io.github.lostblackknight.search.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final HospitalService hospitalService;

    private final DeptService deptService;

    private final ScheduleService scheduleService;

    @PostMapping("/hospitals/upload")
    public CommonResult<?> uploadHospitalToES(@RequestBody HospitalES hospitalES) {
        final HospitalESModel model = new HospitalESModel();
        BeanUtil.copyProperties(hospitalES, model);
        hospitalService.saveHospital(model);
        return CommonResult.success("上传成功");
    }

    @PostMapping("/depts/upload")
    public CommonResult<?> uploadDeptToES(@RequestBody DeptES deptES) {
        final DeptESModel model = new DeptESModel();
        BeanUtil.copyProperties(deptES, model);
        deptService.saveDept(model);
        return CommonResult.success("上传成功");
    }

    @PostMapping("/schedules/upload")
    public CommonResult<?> uploadSchedulesToES(@RequestBody ScheduleES scheduleES) {
        final ScheduleESModel model = new ScheduleESModel();
        BeanUtil.copyProperties(scheduleES, model);
        scheduleService.saveSchedule(model);
        return CommonResult.success("上传成功");
    }
}