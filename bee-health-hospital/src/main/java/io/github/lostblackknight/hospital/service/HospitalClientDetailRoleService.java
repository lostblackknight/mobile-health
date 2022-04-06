package io.github.lostblackknight.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetailRole;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_hospital_client_detail_role】的数据库操作Service
* @createDate 2022-03-30 14:44:35
*/
public interface HospitalClientDetailRoleService extends IService<HospitalClientDetailRole> {

    List<Long> getRoleIdsByHospitalClientDetailId(Long id);
}
