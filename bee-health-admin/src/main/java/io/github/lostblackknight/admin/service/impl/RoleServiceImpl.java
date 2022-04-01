package io.github.lostblackknight.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.admin.mapper.RoleMapper;
import io.github.lostblackknight.admin.service.HospitalClientDetailRoleService;
import io.github.lostblackknight.admin.service.RoleService;
import io.github.lostblackknight.admin.service.UserRoleService;
import io.github.lostblackknight.model.entity.admin.HospitalClientDetailRole;
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
    private final HospitalClientDetailRoleService hospitalClientDetailRoleService;

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
    public List<Role> getRolesByHospitalClientDetailId(Long id) {
        final List<Long> roleIds = hospitalClientDetailRoleService.list(new QueryWrapper<HospitalClientDetailRole>().eq("hospital_client_detail_id", id))
                .stream()
                .map(HospitalClientDetailRole::getRoleId)
                .collect(Collectors.toList());
        return baseMapper.selectBatchIds(roleIds);
    }

    @Override
    public Role getRoleByRoleTag(String tag) {
        return baseMapper.selectOne(new QueryWrapper<Role>().eq("tag", tag));
    }
}




