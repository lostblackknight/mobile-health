package io.github.lostblackknight.order.client;

import io.github.lostblackknight.model.entity.member.Patient;
import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-member")
public interface MemberClient {

    @GetMapping("/patients/{id}")
    CommonResult<Patient> getPatient(@PathVariable Long id);
}
