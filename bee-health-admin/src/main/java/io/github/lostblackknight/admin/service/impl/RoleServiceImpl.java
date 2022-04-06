package io.github.lostblackknight.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.admin.mapper.RoleMapper;
import io.github.lostblackknight.admin.service.RoleService;
import io.github.lostblackknight.admin.service.UserRoleService;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.admin.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_role(角色表)】的数据库操作Service实现
 * @createDate 2022-03-26 18:15:06
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    private final UserRoleService userRoleService;

    @Override
    public List<Role> getRolesByUserId(Long id) {
        final List<Long> roleIds = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", id))
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        // 框架 BUG key 不能作为 属性
        return baseMapper.selectBatchIds(roleIds);
    }

    @Override
    public Role getRoleByRoleTag(String tag) {
        return baseMapper.selectOne(new QueryWrapper<Role>().eq("tag", tag));
    }

    @Override
    public boolean removeRoleById(Long id) {
        final long count = userRoleService.count(new QueryWrapper<UserRole>()
                .eq("role_id", id));
        if (count > 0) {
            return false;
        }
        return removeById(id);
    }

    @Override
    public boolean removeBatchRoleById(List<Long> ids) {
        for (Long id : ids) {
            final long count = userRoleService.count(new QueryWrapper<UserRole>().eq("role_id", id));
            if (count > 0) {
                return false;
            }
        }
        return removeBatchByIds(ids);
    }
}




