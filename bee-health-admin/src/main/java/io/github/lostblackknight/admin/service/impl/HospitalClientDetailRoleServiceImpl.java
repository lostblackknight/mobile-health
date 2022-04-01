package io.github.lostblackknight.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.model.entity.admin.HospitalClientDetailRole;
import io.github.lostblackknight.admin.service.HospitalClientDetailRoleService;
import io.github.lostblackknight.admin.mapper.HospitalClientDetailRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author chensixiang
* @description 针对表【t_hospital_client_detail_role】的数据库操作Service实现
* @createDate 2022-03-30 14:44:35
*/
@Service
@Transactional
public class HospitalClientDetailRoleServiceImpl extends ServiceImpl<HospitalClientDetailRoleMapper, HospitalClientDetailRole>
    implements HospitalClientDetailRoleService{

}




