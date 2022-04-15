package io.github.lostblackknight.search.service;

import io.github.lostblackknight.model.dto.DoctorDTO;
import io.github.lostblackknight.model.dto.ScheduleDateDTO;
import io.github.lostblackknight.model.vo.ScheduleDateParam;
import io.github.lostblackknight.model.vo.ScheduleDoctorParam;
import io.github.lostblackknight.model.vo.ScheduleParam;
import io.github.lostblackknight.search.entity.ScheduleESModel;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public interface ScheduleService {

    void createIndex();

    void deleteIndex();

    void saveSchedule(ScheduleESModel schedule);

    void saveSchedules(List<ScheduleESModel> schedules);

    Iterable<ScheduleESModel> findAll();

    void removeHospital(String scheduleId);

    List<ScheduleESModel> searchSchedule(ScheduleParam param);

    List<ScheduleDateDTO> getScheduleDateList(ScheduleDateParam param);

    List<DoctorDTO> getDoctorList(ScheduleDoctorParam param);
}
