package io.github.lostblackknight.member.controller;

import io.github.lostblackknight.member.client.AdminClient;
import io.github.lostblackknight.member.client.HospitalClient;
import io.github.lostblackknight.member.service.MemberRoleService;
import io.github.lostblackknight.member.service.MemberService;
import io.github.lostblackknight.member.vo.MemberChartVO;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class MemberTotalController {
    private final AdminClient adminClient;
    private final HospitalClient hospitalClient;
    private final MemberRoleService memberRoleService;

    @GetMapping("/members/total/chart")
    public CommonResult<?> getMemberChart() {
        List<MemberChartVO> list = new ArrayList<>();
        List<Role> roleList = new ArrayList<>();
        final CommonResult<List<Role>> result = adminClient.getRolesByList();
        if (result.getCode() == 1) {
            roleList = result.getData();
        }

        for (Role role : roleList) {
            switch (role.getTag()) {
                case "admin": {
                    final CommonResult<Long> result1 = adminClient.getUserCountByRoleId(role.getId());
                    if (result1.getCode() == 1) {
                        final MemberChartVO memberChartVO = new MemberChartVO();
                        memberChartVO.setName(role.getRoleName());
                        memberChartVO.setValue(result1.getData());
                        list.add(memberChartVO);
                    }
                    break;
                }
                case "hospital": {
                    final CommonResult<Long> result1 = hospitalClient.getHospitalCountByRoleId(role.getId());
                    if (result1.getCode() == 1) {
                        final MemberChartVO memberChartVO = new MemberChartVO();
                        memberChartVO.setName(role.getRoleName());
                        memberChartVO.setValue(result1.getData());
                        list.add(memberChartVO);
                    }
                    break;
                }
                case "patient":
                case "doctor": {
                    long count = memberRoleService.getMemberCountByRoleId(role.getId());
                    final MemberChartVO memberChartVO = new MemberChartVO();
                    memberChartVO.setName(role.getRoleName());
                    memberChartVO.setValue(count);
                    list.add(memberChartVO);
                    break;
                }
            }
        }

        return CommonResult.success(list);
    }
}
