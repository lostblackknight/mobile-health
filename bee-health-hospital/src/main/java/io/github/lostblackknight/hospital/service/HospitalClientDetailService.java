package io.github.lostblackknight.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetail;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_hospital_client_detail(医院客户端详情表)】的数据库操作Service
* @createDate 2022-03-26 18:15:21
*/
public interface HospitalClientDetailService extends IService<HospitalClientDetail> {

    boolean modifyHospitalClientDetailStatusById(Long id, Integer status);

    boolean createHospitalClientDetail(HospitalClientDetail hospitalClientDetail);

    boolean removeHospitalClientDetailById(Long id);

    boolean removeBatchHospitalClientDetail(List<Long> ids);
}
