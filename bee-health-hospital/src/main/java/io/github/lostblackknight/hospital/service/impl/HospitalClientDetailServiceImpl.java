package io.github.lostblackknight.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.github.lostblackknight.hospital.client.AdminClient;
import io.github.lostblackknight.hospital.mapper.HospitalClientDetailMapper;
import io.github.lostblackknight.hospital.service.HospitalClientDetailRoleService;
import io.github.lostblackknight.hospital.service.HospitalClientDetailService;
import io.github.lostblackknight.model.entity.admin.Role;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetail;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetailRole;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chensixiang
 * @description 针对表【t_hospital_client_detail(医院客户端详情表)】的数据库操作Service实现
 * @createDate 2022-03-26 18:15:21
 */
@Service
@Transactional
@RequiredArgsConstructor
public class HospitalClientDetailServiceImpl extends ServiceImpl<HospitalClientDetailMapper, HospitalClientDetail>
        implements HospitalClientDetailService {

    private final HospitalClientDetailRoleService hospitalClientDetailRoleService;
    private final AdminClient adminClient;

    @Override
    public boolean modifyHospitalClientDetailStatusById(Long id, Integer status) {
        final HospitalClientDetail hospitalClientDetail = baseMapper.selectById(id);
        hospitalClientDetail.setStatus(status);
        return SqlHelper.retBool(baseMapper.updateById(hospitalClientDetail));
    }

    @Override
    public boolean createHospitalClientDetail(HospitalClientDetail hospitalClientDetail) {
        baseMapper.insert(hospitalClientDetail);
        final HospitalClientDetailRole hospitalClientDetailRole = new HospitalClientDetailRole();
        hospitalClientDetailRole.setHospitalClientDetailId(hospitalClientDetail.getId());
        final CommonResult<Role> result = adminClient.getRoleByTag("hospital");
        if (result.getCode() == 1) {
            if (result.getData() != null) {
                hospitalClientDetailRole.setRoleId(result.getData().getId());
                return hospitalClientDetailRoleService.save(hospitalClientDetailRole);
            }
        }
        return false;
    }

    @Override
    public boolean removeHospitalClientDetailById(Long id) {
        baseMapper.deleteById(id);
        return hospitalClientDetailRoleService.remove(new QueryWrapper<HospitalClientDetailRole>().eq("hospital_client_detail_id", id));
    }

    @Override
    public boolean removeBatchHospitalClientDetail(List<Long> ids) {
        final boolean flag = this.removeByIds(ids);
        ids.forEach(id -> {
            hospitalClientDetailRoleService.remove(new QueryWrapper<HospitalClientDetailRole>().eq("hospital_client_detail_id", id));
        });
        return flag;
    }
}




