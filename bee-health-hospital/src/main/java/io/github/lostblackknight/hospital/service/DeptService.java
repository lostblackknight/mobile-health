package io.github.lostblackknight.hospital.service;

import io.github.lostblackknight.model.dto.DeptDTO;
import io.github.lostblackknight.model.entity.hospital.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_dept】的数据库操作Service
* @createDate 2022-04-03 15:37:03
*/
public interface DeptService extends IService<Dept> {

    List<DeptDTO> getDeptListByHospitalCode(String hospitalCode);
}