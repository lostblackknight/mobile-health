package io.github.lostblackknight.search.service.impl;

import io.github.lostblackknight.model.vo.HospitalSearchParam;
import io.github.lostblackknight.search.entity.DeptESModel;
import io.github.lostblackknight.search.repository.DeptRepository;
import io.github.lostblackknight.search.service.DeptService;
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
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;

    @Override
    public void createIndex() {

    }

    @Override
    public void deleteIndex() {

    }

    @Override
    public void saveDept(DeptESModel dept) {
        deptRepository.save(dept);
    }

    @Override
    public void saveDepts(List<DeptESModel> depts) {
        deptRepository.saveAll(depts);
    }

    @Override
    public Iterable<DeptESModel> findAll() {
        return null;
    }

    @Override
    public void removeDept(String deptCode) {

    }

    @Override
    public List<DeptESModel> searchDept(HospitalSearchParam param) {
        return null;
    }
}
