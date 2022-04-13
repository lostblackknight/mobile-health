package io.github.lostblackknight.search.service;

import io.github.lostblackknight.model.vo.HospitalSearchParam;
import io.github.lostblackknight.search.entity.HospitalESModel;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public interface HospitalService {

    void createIndex();

    void deleteIndex();

    void saveHospital(HospitalESModel hospital);

    void saveHospitals(List<HospitalESModel> hospitals);

    Iterable<HospitalESModel> findAll();

    void removeHospital(String hospitalCode);

    List<HospitalESModel> searchHospital(HospitalSearchParam param);
}
