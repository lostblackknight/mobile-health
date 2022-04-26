package io.github.lostblackknight.topic.service;

import io.github.lostblackknight.model.entity.topic.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_category】的数据库操作Service
* @createDate 2022-04-23 20:08:06
*/
public interface CategoryService extends IService<Category> {

    boolean removeCategoryById(Long id);

    boolean removeBatchCategoryByIds(List<Long> ids);
}
