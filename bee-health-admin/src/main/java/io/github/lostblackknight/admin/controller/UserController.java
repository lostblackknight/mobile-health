package io.github.lostblackknight.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import io.github.lostblackknight.admin.vo.UserAddForm;
import io.github.lostblackknight.admin.service.UserService;
import io.github.lostblackknight.admin.support.TokenInfoContextHolder;
import io.github.lostblackknight.admin.vo.UserEditForm;
import io.github.lostblackknight.admin.vo.UserInfoVO;
import io.github.lostblackknight.admin.vo.UserQueryForm;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.admin.User;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/info")
    public CommonResult<UserInfoVO> getUserInfo() {
        final TokenInfoDTO tokenInfoDTO = TokenInfoContextHolder.get();
        final Long userId = tokenInfoDTO.getUid();
        User user = userService.getUserById(userId);
        return CommonResult.success(
                UserInfoVO.builder()
                        .name(user.getUsername())
                        .avatar(user.getAvatar())
                        .roles(user.getRoles().stream().map(Role::getTag).collect(Collectors.toList()))
                        .build()
        );
    }

    @GetMapping("/users/{id}")
    public CommonResult<User> getUserById(@PathVariable Long id) {
        final User user = userService.getUserById(id);
        return CommonResult.success(user);
    }

    @GetMapping("/users/page/{pageNum}/{pageSize}")
    public CommonResult<PageDTO<User>> getUsersByPage(@PathVariable Long pageNum,
                                                      @PathVariable Long pageSize,
                                                      UserQueryForm userQueryForm) {
        log.info("请求的查询参数为：{}",userQueryForm);
        if (pageNum == null) {
            pageNum = 1L;
        }
        if (pageSize == null) {
            pageSize = 10L;
        }
        User user = new User();
        BeanUtil.copyProperties(userQueryForm, user);
        final PageDTO<User> userPageDTO = userService.getUsersByPage(pageNum, pageSize, user);
        return CommonResult.success(userPageDTO);
    }

    @PostMapping("/users")
    public CommonResult<Void> createUser(@RequestBody UserAddForm form) {
        final User user = new User();
        BeanUtil.copyProperties(form, user);
        return userService.createUser(user, form.getRoleIds()) ? CommonResult.success("添加成功") : CommonResult.fail("添加失败");
    }

    @PutMapping("/users")
    public CommonResult<Void> modifyUser(@RequestBody UserEditForm form) {
        final User user = new User();
        if (form.getId() == null || form.getId() < 0) {
            return CommonResult.fail();
        }
        BeanUtil.copyProperties(form, user);
        return userService.modifyUser(user, form.getRoleIds()) ? CommonResult.success("修改成功") : CommonResult.fail("修改失败");
    }

    @PutMapping("/users/{id}/password/{password}")
    public CommonResult<Void> resetUserPassword(@PathVariable Long id, @PathVariable String password) {
        final boolean flag = userService.resetUserPasswordById(id, password);
        return flag ? CommonResult.success("重置密码成功") : CommonResult.fail("重置密码失败");
    }

    @PutMapping("/users/{id}/status/{status}")
    public CommonResult<?> modifyUserStatusById(@PathVariable Long id, @PathVariable Integer status) {
        boolean flag = userService.modifyUserStatusById(id, status);
        return flag ? CommonResult.success("修改状态成功") : CommonResult.fail("修改状态失败");
    }

    @DeleteMapping("/users/{id}")
    public CommonResult<User> removeUser(@PathVariable Long id) {
        return userService.removeUser(id) ? CommonResult.success("删除成功") : CommonResult.fail("删除失败");
    }

    @DeleteMapping("/users/batch")
    public CommonResult<User> removeBatchUser(@RequestBody List<Long> ids) {
        return userService.removeBatchUser(ids) ? CommonResult.success("删除成功") : CommonResult.fail("删除失败");
    }
}
