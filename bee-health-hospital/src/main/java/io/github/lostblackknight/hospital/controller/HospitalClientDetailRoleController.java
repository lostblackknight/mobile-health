package io.github.lostblackknight.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.lostblackknight.hospital.service.HospitalClientDetailRoleService;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetailRole;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class HospitalClientDetailRoleController {

    private final HospitalClientDetailRoleService hospitalClientDetailRoleService;

    @GetMapping("/hospitalClientDetailRoles/roleId/{roleId}/relation/count")
    public CommonResult<?> getRelationCountByRoleId(@PathVariable Long roleId) {
        final long count = hospitalClientDetailRoleService.count(new QueryWrapper<HospitalClientDetailRole>()
                .eq("role_id", roleId));
        return CommonResult.success(Map.of("count", count));
    }
}
