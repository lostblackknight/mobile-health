package io.github.lostblackknight.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.github.lostblackknight.model.dto.AreaDTO;
import io.github.lostblackknight.admin.mapper.DictMapper;
import io.github.lostblackknight.admin.service.DictService;
import io.github.lostblackknight.model.dto.DictOptionsDTO;
import io.github.lostblackknight.model.entity.admin.Dict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_dict】的数据库操作Service实现
 * @createDate 2022-04-01 19:44:06
 */
@Service
@Transactional
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict>
        implements DictService {

    @Override
    public List<Dict> getDict(String dictLabel) {
        if (StrUtil.isNotEmpty(dictLabel)) {
            return baseMapper.selectList(new QueryWrapper<Dict>().like("dict_label", dictLabel));
        }
        final List<Dict> dictEntityList = baseMapper.selectList(null);
        return dictEntityList.stream()
                .filter(dict -> dict.getParentId() == 0)
                .peek(dict -> {
                    final List<Dict> children = this.getChildren(dict.getId(), dictEntityList);
                    dict.setChildren(children);
                })
                .sorted((o1, o2) -> Math.toIntExact((o1.getDictSort() - o2.getDictSort())))
                .collect(Collectors.toList());
    }

    @Override
    public List<DictOptionsDTO> getDictOptions() {
        final List<Dict> dictEntityList = baseMapper.selectList(null);
        return dictEntityList.stream()
                .filter(dict -> dict.getParentId() == 0)
                .peek(dict -> {
                    final List<Dict> children = this.getChildren(dict.getId(), dictEntityList);
                    dict.setChildren(children);
                })
                .sorted((o1, o2) -> Math.toIntExact((o1.getDictSort() - o2.getDictSort())))
                .map(dict -> {
                    final DictOptionsDTO dictOptionsDTO = new DictOptionsDTO();
                    dictOptionsDTO.setLabel(dict.getDictLabel());
                    dictOptionsDTO.setId(dict.getId());
                    dictOptionsDTO.setChildren(getDictOptionsDTOChildren(dict.getChildren()));
                    return dictOptionsDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeDictById(Long id) {
        final Long count = baseMapper.selectCount(new QueryWrapper<Dict>().eq("parent_id", id));
        if (count > 0) {
            return false;
        }
        return SqlHelper.retBool(baseMapper.deleteById(id));
    }

    @Override
    public boolean removeBatchDictByIds(List<Long> ids) {
        for (Long id : ids) {
            final Long count = baseMapper.selectCount(new QueryWrapper<Dict>().eq("parent_id", id));
            if (count > 0) {
                return false;
            }
        }
        return SqlHelper.retBool(baseMapper.deleteBatchIds(ids));
    }

    @Override
    public List<AreaDTO> getAreaList() {
        final Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>().eq("dict_value", "area_list"));
        final List<Dict> dictList = baseMapper.selectList(null);
        final Long id = dict.getId();

        return getAreaDTOS(dictList, id);
    }

    @Override
    public Dict getDictByDictValue(String dictValue) {
        return baseMapper.selectOne(new QueryWrapper<Dict>().eq("dict_value", dictValue));
    }

    private List<AreaDTO> getAreaDTOS(List<Dict> dictList, Long id) {
        return dictList.stream()
                .filter(dict -> dict.getParentId().equals(id))
                .sorted((o1, o2) -> Math.toIntExact((o1.getDictSort() - o2.getDictSort())))
                .map(dict -> {
                    final AreaDTO areaDTO = new AreaDTO();
                    areaDTO.setLabel(dict.getDictLabel());
                    areaDTO.setValue(dict.getDictValue());
                    if (CollUtil.isEmpty(getAreaListChildren(dict.getId(), dictList))) {
                        areaDTO.setChildren(null);
                    } else {
                        areaDTO.setChildren(getAreaListChildren(dict.getId(), dictList));
                    }
                    return areaDTO;
                })
                .collect(Collectors.toList());
    }

    private List<AreaDTO> getAreaListChildren(Long parentId, List<Dict> dictList) {
        return getAreaDTOS(dictList, parentId);
    }

    private List<DictOptionsDTO> getDictOptionsDTOChildren(List<Dict> children) {
        if (CollUtil.isEmpty(children)) return null;

        return children.stream()
                .map(dict -> {
                    final DictOptionsDTO dictOptionsDTO = new DictOptionsDTO();
                    dictOptionsDTO.setLabel(dict.getDictLabel());
                    dictOptionsDTO.setId(dict.getId());
                    dictOptionsDTO.setChildren(getDictOptionsDTOChildren(dict.getChildren()));
                    return dictOptionsDTO;
                }).collect(Collectors.toList());
    }

    private List<Dict> getChildren(Long parentId, List<Dict> dictList) {
        return dictList.stream()
                .filter(dict -> Objects.equals(dict.getParentId(), parentId))
                .peek(dict -> {
                    final List<Dict> children = getChildren(dict.getId(), dictList);
                    dict.setChildren(children);
                })
                .sorted((o1, o2) -> Math.toIntExact((o1.getDictSort() - o2.getDictSort())))
                .collect(Collectors.toList());
    }
}




