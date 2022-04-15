package io.github.lostblackknight.hospital.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.hospital.service.DeptService;
import io.github.lostblackknight.model.dto.DeptESDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@SpringBootTest
class DeptServiceImplTest {

    @Autowired
    DeptService deptService;

    @Test
    void test19() throws JsonProcessingException {
        final List<DeptESDTO> list = deptService.getDeptListByHospitalCode("200026465");
        System.out.println(new ObjectMapper().writeValueAsString(list));
    }
}