package io.github.lostblackknight.member.service;

import io.github.lostblackknight.model.entity.member.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author chensixiang
* @description 针对表【t_member(会员信息表)】的数据库操作Service
* @createDate 2022-04-12 13:16:28
*/
public interface MemberService extends IService<Member> {

    Member getMemberById(Long memberId);

    Member getMemberByPhone(String phone);
}
