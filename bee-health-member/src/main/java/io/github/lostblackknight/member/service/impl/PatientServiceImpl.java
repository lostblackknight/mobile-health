package io.github.lostblackknight.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.member.support.TokenInfoContextHolder;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.member.Patient;
import io.github.lostblackknight.member.service.PatientService;
import io.github.lostblackknight.member.mapper.PatientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author chensixiang
* @description 针对表【t_patient(就诊人表)】的数据库操作Service实现
* @createDate 2022-04-17 11:59:47
*/
@Service
@Transactional
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient>
    implements PatientService{

    @Override
    public boolean createPatient(Patient patient) {
        final Patient one = baseMapper.selectOne(new QueryWrapper<Patient>().eq("certificates_no", patient.getCertificatesNo()));
        if (one != null) {
            return false;
        }
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final Long uid = dto.getUid();
        patient.setMemberId(uid);
        return save(patient);
    }
}




