package io.github.lostblackknight.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.lostblackknight.admin.dto.DictOptionsDTO;
import io.github.lostblackknight.model.entity.admin.Dict;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_dict】的数据库操作Service
* @createDate 2022-04-01 19:44:06
*/
public interface DictService extends IService<Dict> {

    List<Dict> getDict(String dictLabel);

    List<DictOptionsDTO> getDictOptions();

    boolean removeDictById(Long id);

    boolean removeBatchDictByIds(List<Long> ids);
}
