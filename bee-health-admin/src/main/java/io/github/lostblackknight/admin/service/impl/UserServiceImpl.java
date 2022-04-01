package io.github.lostblackknight.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.admin.mapper.UserMapper;
import io.github.lostblackknight.admin.service.RoleService;
import io.github.lostblackknight.admin.service.UserService;
import io.github.lostblackknight.admin.support.CustomUserDetails;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.admin.User;
import io.github.lostblackknight.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService, UserDetailsService {

    private final RoleService roleService;

    @Override
    public User getUserById(Long id) {
        final User userEntity = baseMapper.selectById(id);
        List<Role> roles = roleService.getRolesByUserId(id);
        userEntity.setRoles(roles);
        return userEntity;
    }

    @Override
    public PageDTO<User> getUsersByPage(Long pageNum, Long pageSize) {
        final Page<User> userPage = baseMapper.selectPage(Page.of(pageNum, pageSize), null);
        final List<User> userList = userPage.getRecords().stream()
                .peek(user -> {
                    final List<Role> roles = roleService.getRolesByUserId(user.getId());
                    user.setRoles(roles);
                }).collect(Collectors.toList());
        userPage.setRecords(userList);
        return PageUtils.toPage(userPage);
    }

    @Override
    public boolean createUser(User user) {
        return save(user);
    }

    @Override
    public boolean updateUserPassword(Long id, String password) {
        return false;
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




