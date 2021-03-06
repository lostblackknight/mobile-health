package io.github.lostblackknight.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.hospital.mapper.DeptMapper;
import io.github.lostblackknight.hospital.service.DeptService;
import io.github.lostblackknight.model.dto.DeptESDTO;
import io.github.lostblackknight.model.entity.hospital.Dept;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_dept】的数据库操作Service实现
 * @createDate 2022-04-03 15:37:03
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
        implements DeptService {

    @Override
    public List<DeptESDTO> getDeptListByHospitalCode(String hospitalCode) {
        final List<Dept> deptList = baseMapper.selectList(new QueryWrapper<Dept>().eq("hospital_code", hospitalCode));

        final List<Dept> bigDeptList = baseMapper.selectList(new QueryWrapper<Dept>()
                .select("distinct class_code, class_name")
                .eq("hospital_code", hospitalCode));

        return bigDeptList.stream()
                .map(bigDept -> {
                    final DeptESDTO dto = new DeptESDTO();
                    dto.setId(bigDept.getClassCode());
                    dto.setText(bigDept.getClassName());
                    final List<DeptESDTO> children = deptList.stream().filter(dept -> dept.getClassCode().equals(bigDept.getClassCode())).map(dept -> {
                        final DeptESDTO deptDTO = new DeptESDTO();
                        deptDTO.setId(dept.getDeptCode());
                        deptDTO.setText(dept.getDeptName());
                        deptDTO.setChildren(null);
                        return deptDTO;
                    }).collect(Collectors.toList());
                    dto.setChildren(children);
                    return dto;
                }).collect(Collectors.toList());
    }
}




