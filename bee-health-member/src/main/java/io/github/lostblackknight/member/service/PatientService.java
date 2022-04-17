package io.github.lostblackknight.member.service;

import io.github.lostblackknight.model.entity.member.Patient;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author chensixiang
* @description 针对表【t_patient(就诊人表)】的数据库操作Service
* @createDate 2022-04-17 11:59:47
*/
public interface PatientService extends IService<Patient> {

    boolean createPatient(Patient patient);
}
