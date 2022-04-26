package io.github.lostblackknight.member.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lostblackknight.member.service.MemberService;
import io.github.lostblackknight.member.support.TokenInfoContextHolder;
import io.github.lostblackknight.member.vo.MemberInfoVO;
import io.github.lostblackknight.member.vo.MemberQueryForm;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.member.Member;
import io.github.lostblackknight.model.entity.member.MemberRole;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/info")
    public CommonResult<?> info() {
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final Long memberId = dto.getUid();
        final Member member = memberService.getById(memberId);
        final MemberInfoVO memberInfoVO = new MemberInfoVO();
        memberInfoVO.setRoles(dto.getRoles());
        memberInfoVO.setUsername(member.getUsername());
        memberInfoVO.setNickName(member.getNickName());
        memberInfoVO.setAvatar(member.getAvatar());
        memberInfoVO.setStatus(member.getStatus());
        memberInfoVO.setUid(memberId);
        return CommonResult.success(memberInfoVO);
    }

    @GetMapping("/members/{id}")
    public CommonResult<?> getMemberById(@PathVariable Long id) {
        final Member member = memberService.getMemberById(id);
        return CommonResult.success(member);
    }

    @GetMapping("/members/memberId")
    public CommonResult<?> getMemberById() {
        final Long uid = TokenInfoContextHolder.get().getUid();
        final Member member = memberService.getMemberById(uid);
        return CommonResult.success(member);
    }

    @PutMapping("/members")
    public CommonResult<?> modifyMember(@RequestBody Member member) {
        if (member.getPassword() != null && member.getPassword().length() != 0) {
            final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            final String encode = encoder.encode(member.getPassword());
            member.setPassword(encode);
        }
        // TODO: 同时更新后台 判断是否为医生
        final boolean flag = memberService.updateById(member);
        return flag ? CommonResult.success("修改成功") : CommonResult.fail("修改失败");
    }

    @GetMapping("/members/page/{pageNum}/{pageSize}")
    public CommonResult<?> getMemberPage(@PathVariable Long pageNum,
                                         @PathVariable Long pageSize,
                                         MemberQueryForm form) {
        final QueryWrapper<Member> wrapper = new QueryWrapper<>();

        wrapper.like(StrUtil.isNotEmpty(form.getUsername()), "username",form.getUsername());
        wrapper.like(StrUtil.isNotEmpty(form.getNickName()), "nick_name",form.getNickName());
        wrapper.like(StrUtil.isNotEmpty(form.getRealName()), "real_name",form.getRealName());
        wrapper.like(StrUtil.isNotEmpty(form.getPhone()), "phone",form.getPhone());

        final Page<Member> page = memberService.page(Page.of(pageNum, pageSize), wrapper);

        final PageDTO<Member> pageDTO = PageUtils.toPage(page);
        return CommonResult.success(pageDTO);
    }
}
