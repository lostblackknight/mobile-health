package io.github.lostblackknight.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.model.entity.member.MemberRole;
import io.github.lostblackknight.member.service.MemberRoleService;
import io.github.lostblackknight.member.mapper.MemberRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_member_role(会员类型关联关系表)】的数据库操作Service实现
 * @createDate 2022-04-12 13:16:34
 */
@Service
public class MemberRoleServiceImpl extends ServiceImpl<MemberRoleMapper, MemberRole>
        implements MemberRoleService {

    @Override
    public List<Long> getRoleIdsByMemberId(Long id) {
        return baseMapper.selectList(new QueryWrapper<MemberRole>()
                .eq("member_id", id))
                .stream()
                .map(MemberRole::getRoleId)
                .collect(Collectors.toList());
    }

    @Override
    public long getMemberCountByRoleId(Long id) {
        return baseMapper.selectCount(new QueryWrapper<MemberRole>().eq("role_id", id));
    }
}




