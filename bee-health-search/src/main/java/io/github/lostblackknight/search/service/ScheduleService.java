package io.github.lostblackknight.search.service;

import io.github.lostblackknight.model.vo.HospitalSearchParam;
import io.github.lostblackknight.search.entity.HospitalESModel;
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

    List<ScheduleESModel> searchSchedule(HospitalSearchParam param);
}
