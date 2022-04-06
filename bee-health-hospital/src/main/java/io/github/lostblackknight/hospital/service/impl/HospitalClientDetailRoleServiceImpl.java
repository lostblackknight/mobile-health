package io.github.lostblackknight.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.hospital.mapper.HospitalClientDetailRoleMapper;
import io.github.lostblackknight.hospital.service.HospitalClientDetailRoleService;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetailRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_hospital_client_detail_role】的数据库操作Service实现
 * @createDate 2022-03-30 14:44:35
 */
@Service
@Transactional
public class HospitalClientDetailRoleServiceImpl extends ServiceImpl<HospitalClientDetailRoleMapper, HospitalClientDetailRole>
        implements HospitalClientDetailRoleService {

    @Override
    public List<Long> getRoleIdsByHospitalClientDetailId(Long id) {
        return baseMapper.selectList(new QueryWrapper<HospitalClientDetailRole>()
                        .eq("hospital_client_detail_id", id))
                .stream()
                .map(HospitalClientDetailRole::getRoleId).collect(Collectors.toList());
    }
}




