package io.github.lostblackknight.member.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.lostblackknight.member.service.PatientService;
import io.github.lostblackknight.member.support.TokenInfoContextHolder;
import io.github.lostblackknight.member.vo.PatientAddForm;
import io.github.lostblackknight.member.vo.PatientEditForm;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.member.Patient;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patients/{id}")
    public CommonResult<?> getPatient(@PathVariable Long id) {
        final Patient patient = patientService.getById(id);
        return CommonResult.success(patient);
    }

    @GetMapping("/patients/list")
    public CommonResult<?> getPatientList() {
        final List<Patient> list = patientService.list();
        return CommonResult.success(list);
    }

    @GetMapping("/patients/memberId")
    public CommonResult<?> getPatientByMemberId() {
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final Long uid = dto.getUid();
        final List<Patient> patientList = patientService.list(new QueryWrapper<Patient>().eq("member_id", uid));
        return CommonResult.success(patientList);
    }

    @PostMapping("/patients")
    public CommonResult<?> createPatient(@RequestBody PatientAddForm form) {
        final Patient patient = new Patient();
        BeanUtil.copyProperties(form, patient);
        final boolean flag = patientService.createPatient(patient);
        return flag ? CommonResult.success("添加成功") : CommonResult.fail("添加失败");
    }

    @PutMapping("/patients")
    public CommonResult<?> modifyPatient(@RequestBody PatientEditForm form) {
        final Patient patient = new Patient();
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final Long uid = dto.getUid();
        patient.setMemberId(uid);
        BeanUtil.copyProperties(form, patient);
        final boolean flag = patientService.updateById(patient);
        return flag ? CommonResult.success("修改成功") : CommonResult.fail("修改失败");
    }

    @DeleteMapping("/patients/{id}")
    public CommonResult<?> removePatient(@PathVariable Long id) {
        final boolean flag = patientService.removeById(id);
        return flag ? CommonResult.success("删除成功") : CommonResult.fail("删除失败");
    }
}
