package io.github.lostblackknight.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.model.entity.hospital.Hospital;
import io.github.lostblackknight.hospital.service.HospitalService;
import io.github.lostblackknight.hospital.mapper.HospitalMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author chensixiang
* @description 针对表【t_hospital】的数据库操作Service实现
* @createDate 2022-04-03 15:37:03
*/
@Service
@Transactional
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital>
    implements HospitalService{

}




