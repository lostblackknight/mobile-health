package io.github.lostblackknight.search.service;

import io.github.lostblackknight.model.vo.HospitalSearchParam;
import io.github.lostblackknight.search.entity.DeptESModel;
import io.github.lostblackknight.search.entity.HospitalESModel;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public interface DeptService {

    void createIndex();

    void deleteIndex();

    void saveDept(DeptESModel dept);

    void saveDepts(List<DeptESModel> depts);

    Iterable<DeptESModel> findAll();

    void removeDept(String deptCode);

    List<DeptESModel> searchDept(HospitalSearchParam param);
}
