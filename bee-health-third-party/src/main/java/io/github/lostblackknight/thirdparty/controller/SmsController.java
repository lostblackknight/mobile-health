package io.github.lostblackknight.thirdparty.controller;

import cn.hutool.log.Log;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.thirdparty.template.SmsCodeTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SmsController {

    private final SmsCodeTemplate smsCodeTemplate;

    @GetMapping("/sms/sendCode/{phone}/{code}")
    public CommonResult<?> sendCode(@PathVariable String code, @PathVariable String phone) {
        log.info("发送验证码手机号为：{}", phone);
        try {
            smsCodeTemplate.sendCode(phone, code);
            return CommonResult.success("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail("发送失败");
        }
    }
}
