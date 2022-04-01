package io.github.lostblackknight.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-hospital")
public interface HospitalClient {

    @GetMapping("/hello")
    String hello();
}
