package io.github.lostblackknight.member.service;

import io.github.lostblackknight.model.entity.member.MemberRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_member_role(会员类型关联关系表)】的数据库操作Service
* @createDate 2022-04-12 13:16:34
*/
public interface MemberRoleService extends IService<MemberRole> {

    List<Long> getRoleIdsByMemberId(Long id);
}
