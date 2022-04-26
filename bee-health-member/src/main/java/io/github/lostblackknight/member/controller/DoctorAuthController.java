package io.github.lostblackknight.member.controller;

import io.github.lostblackknight.member.service.DoctorAuthService;
import io.github.lostblackknight.member.vo.AuthQueryForm;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.entity.member.DoctorAuth;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class DoctorAuthController {

    private final DoctorAuthService doctorAuthService;

    @PutMapping("/doctor/auth")
    public CommonResult<?> authToDoctor(@RequestBody DoctorAuth doctorAuth) {
        final boolean flag = doctorAuthService.authToDoctor(doctorAuth);
        return flag ? CommonResult.success() : CommonResult.fail();
    }

    @GetMapping("/doctor/auth/page/{pageNum}/{pageSize}")
    public CommonResult<?> getAuthList(@PathVariable Long pageNum,
                                       @PathVariable Long pageSize,
                                       AuthQueryForm form) {
        PageDTO<DoctorAuth> dto = doctorAuthService.getAuthList(pageNum, pageSize, form);
        return CommonResult.success(dto);
    }

    @PutMapping("/doctor/auth/unAccess/{id}")
    public CommonResult<?> unAccess(@PathVariable Long id) {
        boolean flag = doctorAuthService.unAccess(id);
        return flag ? CommonResult.success() : CommonResult.fail();
    }

    @PutMapping("/doctor/auth/access/{id}")
    public CommonResult<?> access(@PathVariable Long id) {
        boolean flag = doctorAuthService.access(id);
        return flag ? CommonResult.success() : CommonResult.fail();
    }
}
