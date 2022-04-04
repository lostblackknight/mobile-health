package io.github.lostblackknight.hospital.controller;

import io.github.lostblackknight.hospital.service.DeptService;
import io.github.lostblackknight.model.dto.DeptDTO;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    @GetMapping("/depts/hospitalCode/{hospitalCode}")
    public CommonResult<?> getDeptListByHospitalCode(@PathVariable String hospitalCode) {
        List<DeptDTO> deptDTOS = deptService.getDeptListByHospitalCode(hospitalCode);
        return CommonResult.success(deptDTOS);
    }
}
