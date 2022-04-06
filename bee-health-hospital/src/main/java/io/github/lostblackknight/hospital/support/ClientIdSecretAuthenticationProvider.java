package io.github.lostblackknight.hospital.support;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.lostblackknight.hospital.client.AdminClient;
import io.github.lostblackknight.hospital.service.HospitalClientDetailRoleService;
import io.github.lostblackknight.hospital.service.HospitalClientDetailService;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetail;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final HospitalClientDetailRoleService hospitalClientDetailRoleService;
    private final AdminClient adminClient;

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

        List<Long> roleIds = hospitalClientDetailRoleService.getRoleIdsByHospitalClientDetailId(hospitalClientDetail.getId());
        final CommonResult<List<Role>> result = adminClient.getRolesByIds(roleIds);
        if (result.getCode() == 1) {
            if (CollUtil.isNotEmpty(result.getData())) {
                final List<Role> roles = result.getData();
                Collection<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getTag())).collect(Collectors.toList());
                final ClientIdSecretAuthenticationToken token = new ClientIdSecretAuthenticationToken(hospitalClientDetail, authentication.getCredentials(), authorities);
                token.setDetails(authentication.getDetails());
                return token;
            }
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (ClientIdSecretAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private String determineClientId(Authentication authentication) {
        return (authentication.getPrincipal() == null) ? "" : authentication.getName();
    }
}
