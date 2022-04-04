package io.github.lostblackknight.hospital.controller;

import io.github.lostblackknight.hospital.service.DeptService;
import io.github.lostblackknight.hospital.service.HospitalService;
import io.github.lostblackknight.hospital.service.ScheduleService;
import io.github.lostblackknight.model.entity.hospital.Dept;
import io.github.lostblackknight.model.entity.hospital.Hospital;
import io.github.lostblackknight.model.entity.hospital.Schedule;
import io.github.lostblackknight.model.vo.CommonResult;
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

    /**
     * 上传医院数据
     *
     * @param hospital
     */
    @PostMapping("/hospitals/upload")
    public CommonResult<?> saveHospital(@RequestBody Hospital hospital) {
        final boolean flag = hospitalService.saveOrUpdate(hospital);
        return flag ? CommonResult.success("上传成功") : CommonResult.fail("上传失败");
    }

    private final DeptService deptService;

    /**
     * 上传科室数据
     *
     * @param dept
     * @return
     */
    @PostMapping("/depts/upload")
    public CommonResult<?> saveDept(@RequestBody Dept dept) {
        final boolean flag = deptService.saveOrUpdate(dept);
        return flag ? CommonResult.success("上传成功") : CommonResult.fail("上传失败");
    }

    private final ScheduleService scheduleService;

    /**
     * 上传排班数据
     *
     * @param schedule
     * @return
     */
    @PostMapping("/schedules/upload")
    public CommonResult<?> saveSchedule(@RequestBody Schedule schedule) {
        final boolean flag = scheduleService.saveOrUpdate(schedule);
        return flag ? CommonResult.success("上传成功") : CommonResult.fail("上传失败");
    }
}
