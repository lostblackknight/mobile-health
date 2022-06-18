package io.github.lostblackknight.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.github.lostblackknight.admin.mapper.UserMapper;
import io.github.lostblackknight.admin.service.RoleService;
import io.github.lostblackknight.admin.service.UserRoleService;
import io.github.lostblackknight.admin.service.UserService;
import io.github.lostblackknight.admin.support.CustomUserDetails;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.admin.User;
import io.github.lostblackknight.model.entity.admin.UserRole;
import io.github.lostblackknight.util.PageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_user(用户表)】的数据库操作Service实现
 * @createDate 2022-03-26 18:14:57
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService, UserDetailsService {

    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Override
    public User getUserById(Long id) {
        final User userEntity = baseMapper.selectById(id);
        List<Role> roles = roleService.getRolesByUserId(id);
        userEntity.setRoles(roles);
        return userEntity;
    }

    @Override
    public PageDTO<User> getUsersByPage(Long pageNum, Long pageSize, User user) {
        final QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(user)) {
            if (StrUtil.isNotEmpty(user.getUsername())) {
                wrapper.like("username", user.getUsername());
            }
            if (StrUtil.isNotEmpty(user.getPhone())) {
                wrapper.like("phone", user.getPhone());
            }
            if (StrUtil.isNotEmpty(user.getEmail())) {
                wrapper.like("email", user.getEmail());
            }
        }
        wrapper.orderByDesc("update_time");
        final Page<User> userPage = baseMapper.selectPage(Page.of(pageNum, pageSize), wrapper);
        final List<User> userList = userPage.getRecords().stream()
                .peek(u -> {
                    final List<Role> roles = roleService.getRolesByUserId(u.getId());
                    u.setRoles(roles);
                }).collect(Collectors.toList());
        userPage.setRecords(userList);
        return PageUtils.toPage(userPage);
    }

    @Override
    public boolean createUser(User user, List<Long> roleIds) {
        final String password = user.getPassword();
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encode = encoder.encode(password);
        user.setPassword(encode);
        save(user);
        final List<UserRole> userRoleList = roleIds.stream().map(roleId -> {
            final UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            return userRole;
        }).collect(Collectors.toList());
        return userRoleService.saveBatch(userRoleList);
    }

    @Override
    public boolean updateUserPassword(Long id, String password) {
        return false;
    }

    @Override
    public boolean modifyUser(User user, List<Long> roleIds) {
        updateById(user);
        userRoleService.remove(new QueryWrapper<UserRole>()
                .eq("user_id", user.getId()));
        final List<UserRole> userRoleList = roleIds.stream().map(roleId -> {
            final UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            return userRole;
        }).collect(Collectors.toList());
        return userRoleService.saveBatch(userRoleList);
    }

    @Override
    public boolean removeUser(Long id) {
        removeById(id);
        return userRoleService.remove(new QueryWrapper<UserRole>()
                .eq("user_id", id));
    }

    @Override
    public boolean removeBatchUser(List<Long> ids) {
        removeBatchByIds(ids);
        final long count = ids.stream().peek(id -> {
            userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", id));
        }).count();
        return count == ids.size();
    }

    @Override
    public boolean modifyUserStatusById(Long id, Integer status) {
        final User user = new User();
        user.setId(id);
        user.setStatus(status);
        return SqlHelper.retBool(baseMapper.updateById(user));
    }

    @Override
    public boolean resetUserPasswordById(Long id, String password) {
        final User user = baseMapper.selectById(id);
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encode = encoder.encode(password);
        user.setPassword(encode);
        return SqlHelper.retBool(baseMapper.updateById(user));
    }

    @Override
    public long getUserCountByRoleId(Long id) {
        return userRoleService.count(new QueryWrapper<UserRole>().eq("role_id", id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User userEntity = baseMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username));
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        final List<Role> roles = roleService.getRolesByUserId(userEntity.getId());
        Collection<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTag()))
                .collect(Collectors.toList());
        return new CustomUserDetails(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities,
                true,
                userEntity.getStatus() == 0,
                true,
                true
        );
    }
}




