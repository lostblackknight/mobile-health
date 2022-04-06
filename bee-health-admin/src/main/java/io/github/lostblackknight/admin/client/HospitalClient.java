package io.github.lostblackknight.admin.client;

import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-hospital")
public interface HospitalClient {

    @GetMapping("/hospitalClientDetailRoles/roleId/{roleId}/relation/count")
    CommonResult<Map<String, Object>> getRelationCountByRoleId(@PathVariable Long roleId);
}
