package io.github.lostblackknight.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.member.client.AdminClient;
import io.github.lostblackknight.member.mapper.DoctorAuthMapper;
import io.github.lostblackknight.member.service.DoctorAuthService;
import io.github.lostblackknight.member.service.MemberRoleService;
import io.github.lostblackknight.member.service.MemberService;
import io.github.lostblackknight.member.support.TokenInfoContextHolder;
import io.github.lostblackknight.member.vo.AuthQueryForm;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.member.DoctorAuth;
import io.github.lostblackknight.model.entity.member.Member;
import io.github.lostblackknight.model.entity.member.MemberRole;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chensixiang
 * @description 针对表【t_doctor_auth】的数据库操作Service实现
 * @createDate 2022-04-25 18:52:34
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DoctorAuthServiceImpl extends ServiceImpl<DoctorAuthMapper, DoctorAuth>
        implements DoctorAuthService {

    private final MemberService memberService;
    private final MemberRoleService memberRoleService;
    private final AdminClient adminClient;

    @Override
    public boolean authToDoctor(DoctorAuth doctorAuth) {
        final TokenInfoDTO tokenInfoDTO = TokenInfoContextHolder.get();
        final Long memberId = tokenInfoDTO.getUid();
        final Member member = new Member();
        member.setId(memberId);
        member.setStatus(-1);
        memberService.updateById(member);
        doctorAuth.setStatus(-1L);
        doctorAuth.setMemberId(memberId);
        return saveOrUpdate(doctorAuth);
    }

    @Override
    public PageDTO<DoctorAuth> getAuthList(Long pageNum, Long pageSize, AuthQueryForm form) {
        long from = (pageNum - 1) * pageSize;
        long to = pageSize;
        List<DoctorAuth> records = baseMapper.getAuthList(from, to, form);
        long count = baseMapper.getAuthCount(form);
        final PageDTO<DoctorAuth> dto = new PageDTO<>();
        dto.setPageNum(pageNum);
        dto.setPageSize(pageSize);
        dto.setTotal(count);
        dto.setRecords(records);
        return dto;
    }

    @Override
    public boolean unAccess(Long id) {
        baseMapper.deleteById(id);
        final Member member = new Member();
        member.setId(id);
        member.setStatus(0);
        return memberService.updateById(member);
    }

    @Override
    public boolean access(Long id) {
        // 修改状态
        final DoctorAuth doctorAuth = new DoctorAuth();
        doctorAuth.setMemberId(id);
        doctorAuth.setStatus(1L);
        baseMapper.updateById(doctorAuth);
        final Member member = new Member();
        member.setId(id);
        member.setStatus(1);
        memberService.updateById(member);
        // 将member加入到doctor中
        final MemberRole memberRole = new MemberRole();
        final CommonResult<Role> result = adminClient.getRoleByTag("doctor");
        if (result.getCode() == 1) {
            memberRole.setRoleId(result.getData().getId());
            memberRole.setMemberId(id);
            memberRoleService.save(memberRole);
            return true;
        }
        return false;
    }
}




