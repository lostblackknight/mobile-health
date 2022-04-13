package io.github.lostblackknight.member.client;

import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-third-party")
public interface ThirdPartyClient {

    @GetMapping("/sms/sendCode/{phone}/{code}")
    CommonResult<?> sendCode(@PathVariable String code, @PathVariable String phone);
}
