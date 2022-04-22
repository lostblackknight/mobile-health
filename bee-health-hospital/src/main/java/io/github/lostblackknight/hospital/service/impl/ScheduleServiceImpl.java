package io.github.lostblackknight.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.ScheduleDateDTO;
import io.github.lostblackknight.model.entity.hospital.Schedule;
import io.github.lostblackknight.hospital.service.ScheduleService;
import io.github.lostblackknight.hospital.mapper.ScheduleMapper;
import io.github.lostblackknight.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_schedule】的数据库操作Service实现
 * @createDate 2022-04-03 15:37:03
 */
@Service
@Transactional
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule>
        implements ScheduleService {

    @Override
    public PageDTO<ScheduleDateDTO> getScheduleDatesByHospitalCodeAndDeptCode(String hospitalCode, String deptCode, Long pageNum, Long pageSize) {
        final List<Schedule> scheduleDates = baseMapper.selectList(new QueryWrapper<Schedule>()
                .select("DISTINCT hospital_code, dept_code, date, `week`")
                .eq("hospital_code", hospitalCode)
                .eq("dept_code", deptCode)
                .orderByAsc("date")
        );

        final List<ScheduleDateDTO> collect = scheduleDates.stream().map(schedule -> {
            final ScheduleDateDTO dto = new ScheduleDateDTO();
            dto.setDate(schedule.getDate());
            dto.setWeek(schedule.getWeek());
            final boolean youHao = youHaoByDate(schedule.getHospitalCode(), schedule.getDeptCode(), schedule.getDate());
            dto.setStatus(youHao ? 1 : 0);
            return dto;
        }).collect(Collectors.toList());

        if (pageNum == null) {
            pageNum = 1L;
        }
        if (pageSize == null) {
            pageSize = 5L;
        }

        final Page<ScheduleDateDTO> page = Page.of(pageNum, pageSize);
        long start = (pageNum - 1) * pageSize;
        long limit = pageSize;
        List<ScheduleDateDTO> records;
        if (collect.size() - start < limit) {
            records = collect.subList((int) start, collect.size());
        } else {
            records = collect.subList((int) start, (int) limit);
        }
        page.setRecords(records);
        page.setTotal(collect.size());
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        return PageUtils.toPage(page);
    }

    @Override
    public boolean youHaoByDate(String hospitalCode, String deptCode, String date) {
        final List<Schedule> schedules = baseMapper.selectList(new QueryWrapper<Schedule>()
                .eq("hospital_code", hospitalCode)
                .eq("dept_code", deptCode)
                .eq("date", date)
        );
        final long count = schedules.stream()
                .filter(schedule -> schedule.getYuYueState() > 0)
                .count();
        return count > 0;
    }

    @Override
    public List<Schedule> getDoctorListByScheduleDate(String hospitalCode, String deptCode, String date) {
        return baseMapper.selectList(new QueryWrapper<Schedule>()
                .eq("hospital_code", hospitalCode)
                .eq("dept_code", deptCode)
                .eq("date", date)
                .groupBy("doctor_name")
                .orderByDesc("yu_yue_state")
                .orderByAsc("time_type")
        );
    }
}




