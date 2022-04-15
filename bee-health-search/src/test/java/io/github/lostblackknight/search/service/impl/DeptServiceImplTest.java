package io.github.lostblackknight.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.search.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@SpringBootTest
class DeptServiceImplTest {

    @Autowired
    DeptService deptService;

    @Test
    void searchDeptClassList() throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(deptService.searchDeptClassList("200026465")));
    }
}