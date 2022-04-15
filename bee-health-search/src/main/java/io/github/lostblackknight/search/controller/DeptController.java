package io.github.lostblackknight.search.controller;

import io.github.lostblackknight.model.dto.DeptESDTO;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.model.vo.DeptSearchParam;
import io.github.lostblackknight.search.entity.DeptESModel;
import io.github.lostblackknight.search.service.DeptService;
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
public class DeptController {

    private final DeptService deptService;

    @GetMapping("/dept")
    public CommonResult<?> searchDept(DeptSearchParam param) {
        final List<DeptESModel> deptESModels = deptService.searchDept(param);
        return CommonResult.success(deptESModels);
    }

    @GetMapping("/dept/classList")
    public CommonResult<?> searchDeptClassList(@RequestParam String hospitalCode) {
        final List<DeptESDTO> deptDTOS = deptService.searchDeptClassList(hospitalCode);
        return CommonResult.success(deptDTOS);
    }
}
