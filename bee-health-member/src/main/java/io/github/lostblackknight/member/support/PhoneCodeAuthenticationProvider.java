package io.github.lostblackknight.member.support;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.lostblackknight.constant.RedisConstant;
import io.github.lostblackknight.member.client.AdminClient;
import io.github.lostblackknight.member.service.MemberRoleService;
import io.github.lostblackknight.member.service.MemberService;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetail;
import io.github.lostblackknight.model.entity.member.Member;
import io.github.lostblackknight.model.entity.member.MemberRole;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PhoneCodeAuthenticationProvider implements AuthenticationProvider {

    private final AdminClient adminClient;

    private final MemberService memberService;

    private final MemberRoleService memberRoleService;

    private final StringRedisTemplate redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(PhoneCodeAuthenticationToken.class, authentication,
                () -> "Only PhoneCodeAuthenticationToken is supported");
        String phone = determinePhone(authentication);

        final QueryWrapper<HospitalClientDetail> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(phone)) {
            wrapper.eq("phone", phone);
        }
        log.info("手机号验证码登录的手机号为：{}", phone);

        final Member member = memberService.getMemberByPhone(phone);

        if (member == null) {
            // 用户未注册，自动注册
            final Member autoMember = new Member();
            autoMember.setUsername(phone);
            autoMember.setNickName(phone);
            autoMember.setPhone(phone);
            autoMember.setAvatar("https://s2.loli.net/2022/04/13/dKn7mrQwuUYqVNz.png");
            autoMember.setStatus(0);
            memberService.save(autoMember);
            final CommonResult<Role> result = adminClient.getRoleByTag("patient");
            if (result.getCode() == 1) {
                final Role role = result.getData();
                final MemberRole memberRole = new MemberRole();
                memberRole.setMemberId(autoMember.getId());
                memberRole.setRoleId(role.getId());
                memberRoleService.save(memberRole);
            }
        }

        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("Bad credentials");
        }

        final String redisCode = redisTemplate.opsForValue().get(RedisConstant.SMS_CODE_LOGIN_CACHE_PREFIX + phone);
        if (redisCode != null) {
            final String[] split = redisCode.split("_");
            if (authentication.getCredentials().equals(split[0])) {
                final Member memberEntity = memberService.getMemberByPhone(phone);
                final List<SimpleGrantedAuthority> authorities = memberEntity.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getTag()))
                        .collect(Collectors.toList());
                return new PhoneCodeAuthenticationToken(
                        memberEntity,
                        authentication.getCredentials(),
                        authorities
                );
            }
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (PhoneCodeAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private String determinePhone(Authentication authentication) {
        return (authentication.getPrincipal() == null) ? "" : authentication.getName();
    }
}
