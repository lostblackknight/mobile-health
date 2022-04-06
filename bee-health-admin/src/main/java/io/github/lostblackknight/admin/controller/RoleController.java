package io.github.lostblackknight.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lostblackknight.admin.service.RoleService;
import io.github.lostblackknight.admin.vo.RoleAddForm;
import io.github.lostblackknight.admin.vo.RoleEditForm;
import io.github.lostblackknight.admin.vo.RoleQueryForm;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/roles/tag/{tag}")
    public CommonResult<?> getRoleByTag(@PathVariable String tag) {
        final Role role = roleService.getOne(new QueryWrapper<Role>().eq("tag", tag));
        return CommonResult.success(role);
    }

    @GetMapping("/roles/batch")
    public CommonResult<?> getRolesByIds(@RequestParam List<Long> ids) {
        final List<Role> roles = roleService.listByIds(ids);
        return CommonResult.success(roles);
    }

    @GetMapping("/roles/list")
    public CommonResult<?> getRolesByList() {
        return CommonResult.success(roleService.list());
    }

    @GetMapping("/roles/{id}")
    public CommonResult<?> getRoleById(@PathVariable Long id) {
        return CommonResult.success(roleService.getById(id));
    }

    @GetMapping("/roles/page/{pageNum}/{pageSize}")
    public CommonResult<?> getRolesByPage(@PathVariable Long pageSize,
                                          @PathVariable Long pageNum,
                                          RoleQueryForm form) {
        final Role role = new Role();
        BeanUtil.copyProperties(form, role);
        final QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(form)) {
            if (StrUtil.isNotEmpty(form.getRoleName())) {
                wrapper.like("role_name", form.getRoleName());
            }
            if (StrUtil.isNotEmpty(form.getTag())) {
                wrapper.eq("tag", form.getTag());
            }
        }
        final Page<Role> rolePage = roleService.page(Page.of(pageNum, pageSize), wrapper);
        final PageDTO<Role> rolePageDTO = PageUtils.toPage(rolePage);
        return CommonResult.success(rolePageDTO);
    }

    @PostMapping("/roles")
    public CommonResult<?> createRole(@RequestBody RoleAddForm form) {
        final Role role = new Role();
        BeanUtil.copyProperties(form,role);
        final boolean flag = roleService.save(role);
        return flag ? CommonResult.success("创建成功"): CommonResult.fail("创建失败");
    }

    @PutMapping("/roles")
    public CommonResult<?> modifyRole(@RequestBody RoleEditForm form) {
        final Role role = new Role();
        BeanUtil.copyProperties(form, role);
        final boolean flag = roleService.updateById(role);
        return flag ? CommonResult.success("修改成功") : CommonResult.fail("修改失败");
    }

    @DeleteMapping("/roles/{id}")
    public CommonResult<?> removeRoleById(@PathVariable Long id) {
        final boolean flag = roleService.removeRoleById(id);
        return flag ? CommonResult.success("删除成功"): CommonResult.fail("删除失败，存在关联项");
    }

    @DeleteMapping("/roles/batch")
    public CommonResult<?> removeBatchRoleById(@RequestBody List<Long> ids) {
        final boolean flag = roleService.removeBatchRoleById(ids);
        return flag ? CommonResult.success("删除成功"): CommonResult.fail("删除失败，存在关联项");
    }
}
