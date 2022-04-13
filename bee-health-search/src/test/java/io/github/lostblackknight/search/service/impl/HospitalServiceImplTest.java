package io.github.lostblackknight.search.service.impl;

import io.github.lostblackknight.model.vo.HospitalSearchParam;
import io.github.lostblackknight.search.entity.HospitalESModel;
import io.github.lostblackknight.search.service.HospitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@SpringBootTest
class HospitalServiceImplTest {

    @Autowired
    HospitalService hospitalService;

    @Test
    void createIndex() {
        hospitalService.createIndex();
    }

    @Test
    void deleteIndex() {
        hospitalService.deleteIndex();
    }

    @Test
    void save() {
    }

    @Test
    void saveAll() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByHospitalNameAndConditions() {
    }

    @Test
    void search() {
        final HospitalSearchParam param = new HospitalSearchParam();
        param.setHospitalName("医院");
        param.setLevelName("其他");
//        param.setClassName("儿童医院");
        final List<HospitalESModel> hospitalESModels = hospitalService.searchHospital(param);
        System.out.println(hospitalESModels);
    }
}