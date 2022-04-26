package io.github.lostblackknight.hospital.service;

import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.ScheduleDateDTO;
import io.github.lostblackknight.model.entity.hospital.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author chensixiang
 * @description 针对表【t_schedule】的数据库操作Service
 * @createDate 2022-04-03 15:37:03
 */
public interface ScheduleService extends IService<Schedule> {

    PageDTO<ScheduleDateDTO> getScheduleDatesByHospitalCodeAndDeptCode(String hospitalCode, String deptCode, Long pageNum, Long pageSize, String date);

    /**
     * 判断某个医院某个科室某一天是否有号
     * @param hospitalCode
     * @param deptCode
     * @param date
     * @return
     */
    boolean youHaoByDate(String hospitalCode, String deptCode, String date);

    List<Schedule> getDoctorListByScheduleDate(String hospitalCode, String deptCode, String date);
}
