package io.github.lostblackknight.hospital.controller;

import cn.hutool.core.bean.BeanUtil;
import io.github.lostblackknight.hospital.client.AdminClient;
import io.github.lostblackknight.hospital.client.SearchClient;
import io.github.lostblackknight.hospital.service.DeptService;
import io.github.lostblackknight.hospital.service.HospitalService;
import io.github.lostblackknight.hospital.service.ScheduleService;
import io.github.lostblackknight.model.entity.admin.Dict;
import io.github.lostblackknight.model.entity.hospital.Dept;
import io.github.lostblackknight.model.entity.hospital.Hospital;
import io.github.lostblackknight.model.entity.hospital.Schedule;
import io.github.lostblackknight.model.entity.search.DeptES;
import io.github.lostblackknight.model.entity.search.HospitalES;
import io.github.lostblackknight.model.entity.search.ScheduleES;
import io.github.lostblackknight.model.entity.search.SuggestES;
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

    private final SearchClient searchClient;

    private final AdminClient adminClient;

    /**
     * 上传医院数据
     *
     * @param hospital
     */
    @PostMapping("/hospitals/upload")
    public CommonResult<?> saveHospital(@RequestBody Hospital hospital) {
        final boolean flag = hospitalService.saveOrUpdate(hospital);
        if (hospital.getStatus() == 0) {
            final HospitalES hospitalES = new HospitalES();
            BeanUtil.copyProperties(hospital, hospitalES);
            final CommonResult<Dict> result = adminClient.getDictByDictValue(hospital.getProvinceCode());
            if (result.getCode() == 1) {
                hospitalES.setProvince(result.getData().getDictLabel());
            }
            final CommonResult<Dict> result1 = adminClient.getDictByDictValue(hospital.getCityCode());
            if (result1.getCode() == 1) {
                hospitalES.setCity(result1.getData().getDictLabel());
            }
            searchClient.uploadHospitalToES(hospitalES);
            // 上传建议
            final SuggestES suggestES = new SuggestES();
            suggestES.setId(hospital.getHospitalName());
            suggestES.setSuggest(hospital.getHospitalName());
            searchClient.uploadSuggestToES(suggestES);
        }
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
        final Hospital hospital = hospitalService.getById(dept.getHospitalCode());
        if (hospital != null) {
            if (hospital.getStatus() == 0) {
                final DeptES deptES = new DeptES();
                BeanUtil.copyProperties(dept, deptES);
                final CommonResult<Dict> result = adminClient.getDictByDictValue(hospital.getProvinceCode());
                if (result.getCode() == 1) {
                    deptES.setProvince(result.getData().getDictLabel());
                }
                final CommonResult<Dict> result1 = adminClient.getDictByDictValue(hospital.getCityCode());
                if (result1.getCode() == 1) {
                    deptES.setCity(result1.getData().getDictLabel());
                }
                searchClient.uploadDeptToES(deptES);
                // 上传建议
                final SuggestES suggestES = new SuggestES();
                suggestES.setId(dept.getDeptName());
                suggestES.setSuggest(dept.getDeptName());
                searchClient.uploadSuggestToES(suggestES);
            }
        }
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
        final Hospital hospital = hospitalService.getById(schedule.getHospitalCode());
        final Dept dept = deptService.getById(schedule.getDeptCode());
        if (hospital != null && dept != null) {
            final ScheduleES scheduleES = new ScheduleES();
            BeanUtil.copyProperties(schedule, scheduleES);
            searchClient.uploadSchedulesToES(scheduleES);
            // 上传建议
            final SuggestES suggestES = new SuggestES();
            suggestES.setId(schedule.getDoctorName());
            suggestES.setSuggest(schedule.getDoctorName());
            searchClient.uploadSuggestToES(suggestES);
        }
        return flag ? CommonResult.success("上传成功") : CommonResult.fail("上传失败");
    }
}
