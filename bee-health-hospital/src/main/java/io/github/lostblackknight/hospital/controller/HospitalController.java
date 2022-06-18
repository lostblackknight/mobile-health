package io.github.lostblackknight.hospital.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.entity.hospital.Hospital;
import io.github.lostblackknight.hospital.service.HospitalService;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 医院控制器
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/hospitals/count/roleId/{id}")
    public CommonResult<Long> getHospitalCountByRoleId(@PathVariable Long id) {
        final long count = hospitalService.getHospitalCountByRoleId(id);
        return CommonResult.success(count);
    }

    @GetMapping("/hospitals/page/{pageNum}/{pageSize}")
    public CommonResult<?> getHospitalsByPage(@PathVariable Long pageNum,
                                              @PathVariable Long pageSize,
                                              @RequestParam(required = false) String cityCode,
                                              @RequestParam(required = false) String hospitalName) {
        final QueryWrapper<Hospital> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(cityCode)) {
            wrapper.eq("city_code", cityCode);
        }
        if (StrUtil.isNotEmpty(hospitalName)) {
            wrapper.like("hospital_name", hospitalName);
        }
        final Page<Hospital> hospitalPage = hospitalService.page(Page.of(pageNum, pageSize), wrapper);
        final PageDTO<Hospital> hospitalPageDTO = PageUtils.toPage(hospitalPage);
        return CommonResult.success(hospitalPageDTO);
    }

    @GetMapping("/hospitals/hospitalName/{hospitalName}")
    public CommonResult<?> getHospitalByHospitalName(@PathVariable String hospitalName) {
        final Hospital hospital = hospitalService.getOne(new QueryWrapper<Hospital>()
                .like("hospital_name", hospitalName));
        return CommonResult.success(hospital);
    }

    @GetMapping("/hospitals/{hospitalCode}")
    public CommonResult<?> getHospitalByHospitalCode(@PathVariable String hospitalCode) {
        final Hospital hospital = hospitalService.getById(hospitalCode);
        return CommonResult.success(hospital);
    }

    @PutMapping("/hospitals/{id}/status/{status}")
    public CommonResult<?> modifyHospitalStatusById(@PathVariable String id, @PathVariable Integer status) {
        final Hospital hospital = new Hospital();
        hospital.setHospitalCode(id);
        hospital.setStatus(status);
        final boolean flag = hospitalService.updateById(hospital);
        return flag ? CommonResult.success("修改状态成功"): CommonResult.fail("修改状态失败");
    }
}
