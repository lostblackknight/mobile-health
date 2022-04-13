package io.github.lostblackknight.member.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.github.lostblackknight.constant.RedisConstant;
import io.github.lostblackknight.member.client.ThirdPartyClient;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final ThirdPartyClient thirdPartyClient;
    private final StringRedisTemplate redisTemplate;

    @GetMapping("/sms/sendCode/{phone}")
    public CommonResult<?> sendCode(@PathVariable String phone) {
        final String redisCode = redisTemplate.opsForValue().get(RedisConstant.SMS_CODE_LOGIN_CACHE_PREFIX + phone);
        if (StrUtil.isNotEmpty(redisCode)) {
            final String[] split = redisCode.split("_");
            long instance = System.currentTimeMillis() - Long.parseLong(split[1]);
            if (TimeUnit.MILLISECONDS.toSeconds(instance) <= 60) {
                return CommonResult.fail("验证码获取频率太高，请稍后再试");
            }
        }
        final String code = RandomUtil.randomNumbers(4);
        redisTemplate.opsForValue().set(RedisConstant.SMS_CODE_LOGIN_CACHE_PREFIX + phone, code + "_" + System.currentTimeMillis(), 10L, TimeUnit.MINUTES);
        thirdPartyClient.sendCode(code, phone);
        return CommonResult.success("验证码发送成功");
    }
}
