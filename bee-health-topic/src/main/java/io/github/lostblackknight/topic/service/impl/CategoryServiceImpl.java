package io.github.lostblackknight.topic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.model.entity.topic.Category;
import io.github.lostblackknight.topic.service.ArticleService;
import io.github.lostblackknight.topic.service.CategoryService;
import io.github.lostblackknight.topic.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_category】的数据库操作Service实现
* @createDate 2022-04-23 20:08:06
*/
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    private final ArticleService articleService;

    @Override
    public boolean removeCategoryById(Long id) {
        long count = articleService.getCountByCategoryId(id);
        if (count > 0) {
            return false;
        }
        return removeById(id);
    }

    @Override
    public boolean removeBatchCategoryByIds(List<Long> ids) {
        for (Long id : ids) {
            final long count = articleService.getCountByCategoryId(id);
            if (count > 0) {
                return false;
            }
        }
        return removeBatchByIds(ids);
    }
}




