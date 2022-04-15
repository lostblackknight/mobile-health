package io.github.lostblackknight.search.controller;

import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.model.vo.HospitalSearchParam;
import io.github.lostblackknight.search.entity.HospitalESModel;
import io.github.lostblackknight.search.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/hospital")
    public CommonResult<?> searchHospital(HospitalSearchParam param) {
        final List<HospitalESModel> searchHospital = hospitalService.searchHospital(param);
        return CommonResult.success(searchHospital);
    }
}
