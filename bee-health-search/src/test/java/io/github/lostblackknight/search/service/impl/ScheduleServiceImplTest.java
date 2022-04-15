package io.github.lostblackknight.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.model.dto.DoctorDTO;
import io.github.lostblackknight.model.vo.ScheduleDateParam;
import io.github.lostblackknight.model.vo.ScheduleDoctorParam;
import io.github.lostblackknight.search.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@SpringBootTest
class ScheduleServiceImplTest {

    @Autowired
    ScheduleService scheduleService;

    @Test
    void getScheduleDateList() throws JsonProcessingException {
        final ScheduleDateParam param = new ScheduleDateParam();
        param.setHospitalCode("200026465");
        param.setDeptCode("200102855");

        final ObjectMapper mapper = new ObjectMapper();

        System.out.println(mapper.writeValueAsString(scheduleService.getScheduleDateList(param)));
    }

    @Test
    void getDoctorList() throws JsonProcessingException {
        final ScheduleDoctorParam param = new ScheduleDoctorParam();
        param.setHospitalCode("200026465");
        param.setDeptCode("200102855");
        param.setDate("2022-04-05");
        final List<DoctorDTO> doctorList = scheduleService.getDoctorList(param);
        System.out.println(new ObjectMapper().writeValueAsString(doctorList));
    }
}