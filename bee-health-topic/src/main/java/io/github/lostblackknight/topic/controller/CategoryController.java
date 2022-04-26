package io.github.lostblackknight.topic.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.topic.Category;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.topic.service.CategoryService;
import io.github.lostblackknight.topic.support.TokenInfoContextHolder;
import io.github.lostblackknight.topic.vo.CategoryAddForm;
import io.github.lostblackknight.topic.vo.CategoryEditForm;
import io.github.lostblackknight.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category/list")
    public CommonResult<?> getCategoryList() {
        return CommonResult.success(categoryService.list());
    }

    @GetMapping("/category/page/{pageNum}/{pageSize}")
    public CommonResult<?> getCategoryByPage(@PathVariable Long pageNum,
                                             @PathVariable Long pageSize,
                                             @RequestParam(required = false) String name) {
        final QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        final Page<Category> page = categoryService.page(Page.of(pageNum, pageSize), wrapper);
        final PageDTO<Category> categoryPageDTO = PageUtils.toPage(page);
        return CommonResult.success(categoryPageDTO);
    }

    @GetMapping("/category/{id}")
    public CommonResult<?> getCategoryById(@PathVariable Long id) {
        final Category category = categoryService.getById(id);
        return CommonResult.success(category);
    }

    @PostMapping("/category")
    public CommonResult<?> createCategory(@RequestBody CategoryAddForm form) {
        final Category category = new Category();
        BeanUtil.copyProperties(form, category);
        final boolean flag = categoryService.save(category);
        return flag ? CommonResult.success("添加成功") : CommonResult.fail("添加失败");
    }

    @PutMapping("/category")
    public CommonResult<?> modifyCategory(@RequestBody CategoryEditForm form) {
        final Category category = new Category();
        BeanUtil.copyProperties(form, category);
        final boolean flag = categoryService.updateById(category);
        return flag ? CommonResult.success("修改成功") : CommonResult.fail("修改失败");
    }

    @DeleteMapping("/category/{id}")
    public CommonResult<?> removeCategoryById(@PathVariable Long id) {
        final boolean flag = categoryService.removeCategoryById(id);
        return flag ? CommonResult.success("删除成功") : CommonResult.fail("删除失败，存在关联项");
    }

    @DeleteMapping("/category/batch")
    public CommonResult<?> removeBatchCategoryByIds(@RequestBody List<Long> ids) {
        final boolean flag = categoryService.removeBatchCategoryByIds(ids);
        return flag ? CommonResult.success("删除成功") : CommonResult.fail("删除失败，存在关联项");
    }
}
