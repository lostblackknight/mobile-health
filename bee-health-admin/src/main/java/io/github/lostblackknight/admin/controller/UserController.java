package io.github.lostblackknight.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import io.github.lostblackknight.admin.controller.vo.UserForm;
import io.github.lostblackknight.admin.service.UserService;
import io.github.lostblackknight.admin.support.TokenInfoContextHolder;
import io.github.lostblackknight.admin.vo.UserInfoVO;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.admin.User;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
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

    @GetMapping("/users")
    public CommonResult<PageDTO<User>> getUsersByPage(@RequestParam(required = false) Long pageNum,
                                                      @RequestParam(required = false) Long pageSize) {
        if (pageNum == null) {
            pageNum = 1L;
        }
        if (pageSize == null) {
            pageSize = 10L;
        }
        final PageDTO<User> userPageDTO = userService.getUsersByPage(pageNum, pageSize);
        return CommonResult.success(userPageDTO);
    }

    @PostMapping("/users")
    public CommonResult<Void> createUser(@RequestBody UserForm form) {
        final User user = new User();
        BeanUtil.copyProperties(form, user);
        return userService.createUser(user) ? CommonResult.success() : CommonResult.fail();
    }

    @PutMapping("/users")
    public CommonResult<Void> updateUser(@RequestBody UserForm form) {
        final User user = new User();
        if (form.getId() == null || form.getId() < 0) {
            return CommonResult.fail();
        }
        BeanUtil.copyProperties(form, user);
        return userService.updateById(user) ? CommonResult.success() : CommonResult.fail();
    }

    @PostMapping("/users/update/password")
    public CommonResult<Void> updateUserPassword(@RequestBody UserForm form) {
        return userService.updateUserPassword(form.getId(), form.getPassword()) ? CommonResult.success() : CommonResult.fail();
    }

    @DeleteMapping("/users/{id}")
    public CommonResult<User> deleteUser(@PathVariable Long id) {
        return userService.removeById(id) ? CommonResult.success() : CommonResult.fail();
    }

    @DeleteMapping("/users")
    public CommonResult<User> deleteBatchUser(@RequestParam("userId") List<Long> userIds) {
        return userService.removeBatchByIds(userIds) ? CommonResult.success() : CommonResult.fail();
    }
}
