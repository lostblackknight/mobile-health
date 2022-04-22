package io.github.lostblackknight.message.config;

import io.github.lostblackknight.model.entity.member.Member;
import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-member")
public interface MemberClient {

    @GetMapping("/members/{id}")
    CommonResult<Member> getMemberById(@PathVariable Long id);
}
