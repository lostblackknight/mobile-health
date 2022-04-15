package io.github.lostblackknight.search.service;

import io.github.lostblackknight.model.dto.DeptESDTO;
import io.github.lostblackknight.model.vo.DeptSearchParam;
import io.github.lostblackknight.search.entity.DeptESModel;

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

    List<DeptESModel> searchDept(DeptSearchParam param);

    List<DeptESDTO> searchDeptClassList(String hospitalCode);
}
