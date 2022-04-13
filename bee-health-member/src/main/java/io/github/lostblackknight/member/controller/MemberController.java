package io.github.lostblackknight.member.controller;

import io.github.lostblackknight.member.service.MemberService;
import io.github.lostblackknight.member.support.TokenInfoContextHolder;
import io.github.lostblackknight.member.vo.MemberInfoVO;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.member.Member;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/info")
    public CommonResult<?> hello() {
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final Long memberId = dto.getUid();
        final Member member = memberService.getById(memberId);
        final MemberInfoVO memberInfoVO = new MemberInfoVO();
        memberInfoVO.setRoles(dto.getRoles());
        memberInfoVO.setUsername(member.getUsername());
        memberInfoVO.setNickName(member.getNickName());
        memberInfoVO.setAvatar(member.getAvatar());
        memberInfoVO.setStatus(member.getStatus());
        return CommonResult.success(memberInfoVO);
    }
}
