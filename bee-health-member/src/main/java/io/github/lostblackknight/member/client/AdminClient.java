package io.github.lostblackknight.member.client;

import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-admin")
public interface AdminClient {

    @GetMapping("/roles/tag/{tag}")
    CommonResult<Role> getRoleByTag(@PathVariable String tag);

    @GetMapping("/roles/batch")
    CommonResult<List<Role>> getRolesByIds(@RequestParam List<Long> ids);

    @GetMapping("/roles/list")
    CommonResult<List<Role>> getRolesByList();

    @GetMapping("/users/count/roleId/{id}")
    CommonResult<Long> getUserCountByRoleId(@PathVariable Long id);
}
