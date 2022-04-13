package io.github.lostblackknight.member.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.member.client.AdminClient;
import io.github.lostblackknight.member.service.MemberRoleService;
import io.github.lostblackknight.member.support.CustomUserDetails;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.member.Member;
import io.github.lostblackknight.member.service.MemberService;
import io.github.lostblackknight.member.mapper.MemberMapper;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_member(会员信息表)】的数据库操作Service实现
 * @createDate 2022-04-12 13:16:28
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
        implements MemberService, UserDetailsService {

    private final AdminClient adminClient;
    private final MemberRoleService memberRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Member memberEntity = baseMapper.selectOne(new QueryWrapper<Member>()
                .eq("username", username));
        if (memberEntity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<Long> roleIds = memberRoleService.getRoleIdsByMemberId(memberEntity.getId());
        final CommonResult<List<Role>> result = adminClient.getRolesByIds(roleIds);
        if (result.getCode() == 1) {
            if (CollUtil.isNotEmpty(result.getData())) {
                final List<Role> roles = result.getData();
                Collection<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getTag())).collect(Collectors.toList());
                return new CustomUserDetails(
                        memberEntity.getId(),
                        memberEntity.getUsername(),
                        memberEntity.getPassword(),
                        authorities,
                        true,
                        true,
                        true,
                        true
                );
            }
        }
        throw new UsernameNotFoundException("用户名不存在");
    }

    @Override
    public Member getMemberById(Long memberId) {
        final Member member = getById(memberId);
        final List<Long> roleIds = memberRoleService.getRoleIdsByMemberId(memberId);
        final CommonResult<List<Role>> result = adminClient.getRolesByIds(roleIds);
        if (result.getCode() == 1) {
            if (CollUtil.isNotEmpty(result.getData())) {
                final List<Role> roles = result.getData();
                member.setRoles(roles);
            }
        }
        return member;
    }

    @Override
    public Member getMemberByPhone(String phone) {
        final Member member = baseMapper.selectOne(new QueryWrapper<Member>().eq("phone", phone));
        if (member == null) {
            return null;
        }
        final List<Long> roleIds = memberRoleService.getRoleIdsByMemberId(member.getId());
        final CommonResult<List<Role>> result = adminClient.getRolesByIds(roleIds);
        if (result.getCode() == 1) {
            if (CollUtil.isNotEmpty(result.getData())) {
                final List<Role> roles = result.getData();
                member.setRoles(roles);
            }
        }
        return member;
    }
}




