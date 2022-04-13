package io.github.lostblackknight.search.service.impl;

import io.github.lostblackknight.model.vo.HospitalSearchParam;
import io.github.lostblackknight.search.entity.ScheduleESModel;
import io.github.lostblackknight.search.repository.ScheduleRepository;
import io.github.lostblackknight.search.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void createIndex() {

    }

    @Override
    public void deleteIndex() {

    }

    @Override
    public void saveSchedule(ScheduleESModel schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void saveSchedules(List<ScheduleESModel> schedules) {
        scheduleRepository.saveAll(schedules);
    }

    @Override
    public Iterable<ScheduleESModel> findAll() {
        return null;
    }

    @Override
    public void removeHospital(String scheduleId) {

    }

    @Override
    public List<ScheduleESModel> searchSchedule(HospitalSearchParam param) {
        return null;
    }
}
