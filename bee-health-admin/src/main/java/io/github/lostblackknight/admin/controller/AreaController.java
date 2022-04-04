package io.github.lostblackknight.admin.controller;

import io.github.lostblackknight.admin.service.DictService;
import io.github.lostblackknight.model.dto.AreaDTO;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class AreaController {

    private final DictService dictService;

    @GetMapping("/areas/list")
    public CommonResult<?> getAreaList() {
        List<AreaDTO> areaList = dictService.getAreaList();
        return CommonResult.success(areaList);
    }
}
