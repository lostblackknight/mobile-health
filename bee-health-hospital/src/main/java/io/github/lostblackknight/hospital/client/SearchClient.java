package io.github.lostblackknight.hospital.client;

import io.github.lostblackknight.model.entity.search.DeptES;
import io.github.lostblackknight.model.entity.search.HospitalES;
import io.github.lostblackknight.model.entity.search.ScheduleES;
import io.github.lostblackknight.model.entity.search.SuggestES;
import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-search")
public interface SearchClient {

    @PostMapping("/hospitals/upload")
    CommonResult<?> uploadHospitalToES(@RequestBody HospitalES hospitalES);

    @PostMapping("/depts/upload")
    CommonResult<?> uploadDeptToES(@RequestBody DeptES deptES);

    @PostMapping("/schedules/upload")
    CommonResult<?> uploadSchedulesToES(@RequestBody ScheduleES scheduleES);

    @PostMapping("/suggests/upload")
    CommonResult<?> uploadSuggestToES(@RequestBody SuggestES suggestES);

}
