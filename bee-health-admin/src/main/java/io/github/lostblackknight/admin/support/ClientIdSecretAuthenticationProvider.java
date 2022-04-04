package io.github.lostblackknight.admin.support;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.lostblackknight.admin.client.HospitalClient;
import io.github.lostblackknight.admin.service.HospitalClientDetailRoleService;
import io.github.lostblackknight.admin.service.HospitalClientDetailService;
import io.github.lostblackknight.admin.service.RoleService;
import io.github.lostblackknight.model.entity.admin.HospitalClientDetail;
import io.github.lostblackknight.model.entity.admin.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ClientIdSecretAuthenticationProvider implements AuthenticationProvider {

    private final HospitalClientDetailService hospitalClientDetailService;
    private final RoleService roleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(ClientIdSecretAuthenticationToken.class, authentication,
                () -> "Only UsernamePasswordAuthenticationToken is supported");
        String clientId = determineClientId(authentication);
        final QueryWrapper<HospitalClientDetail> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(clientId)) {
            wrapper.eq("hospital_id", clientId);
        }
        log.info("请求的客户端id为：{}", clientId);
        final HospitalClientDetail hospitalClientDetail = hospitalClientDetailService.getOne(wrapper);
        if (hospitalClientDetail == null || hospitalClientDetail.getStatus() != 0) {
            throw new BadCredentialsException("Bad credentials");
        }

        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("Bad credentials");
        }

        if (!authentication.getCredentials().equals(hospitalClientDetail.getHospitalSecret())) {
            throw new BadCredentialsException("Bad credentials");
        }

        List<Role> roles = roleService.getRolesByHospitalClientDetailId(hospitalClientDetail.getId());
        Collection<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getTag())).collect(Collectors.toList());
        final ClientIdSecretAuthenticationToken result = new ClientIdSecretAuthenticationToken(hospitalClientDetail, authentication.getCredentials(), authorities);
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (ClientIdSecretAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private String determineClientId(Authentication authentication) {
        return (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
    }
}
