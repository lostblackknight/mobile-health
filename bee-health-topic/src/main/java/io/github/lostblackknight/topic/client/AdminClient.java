package io.github.lostblackknight.topic.client;

import io.github.lostblackknight.model.entity.admin.User;
import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-admin")
public interface AdminClient {

    @GetMapping("/users/{id}")
    CommonResult<User> getUserById(@PathVariable Long id);
}
