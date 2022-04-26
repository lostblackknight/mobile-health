package io.github.lostblackknight.topic.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.topic.Article;
import io.github.lostblackknight.model.entity.topic.Category;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.topic.service.ArticleService;
import io.github.lostblackknight.topic.service.CategoryService;
import io.github.lostblackknight.topic.support.TokenInfoContextHolder;
import io.github.lostblackknight.topic.vo.ArticleAddForm;
import io.github.lostblackknight.topic.vo.ArticleEditForm;
import io.github.lostblackknight.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    @GetMapping("/article/page/{pageNum}/{pageSize}")
    public CommonResult<?> getArticleByPage(@PathVariable Long pageNum,
                                             @PathVariable Long pageSize,
                                             @RequestParam(required = false) String title,
                                            @RequestParam(required = false) Long categoryId) {
        final QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(title)) {
            wrapper.like("title", title);
        }
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final Long uid = dto.getUid();
        final boolean isDoctor = dto.getRoles().contains("doctor");
        if (isDoctor) {
            wrapper.eq("uid", uid);
        }
        if (categoryId != null) {
            wrapper.eq("category_id", categoryId);
        }
        final Page<Article> page = articleService.page(Page.of(pageNum, pageSize), wrapper);
        final List<Article> records = page.getRecords();
        final List<Article> collect = records.stream().peek(article -> {
            final Category category = categoryService.getById(article.getCategoryId());
            article.setCategory(category);
        }).collect(Collectors.toList());
        page.setRecords(collect);
        final PageDTO<Article> articlePageDTO = PageUtils.toPage(page);
        return CommonResult.success(articlePageDTO);
    }

    @GetMapping("/article/{id}")
    public CommonResult<?> getArticleById(@PathVariable Long id) {
        final Article article = articleService.getById(id);
        final Category category = categoryService.getById(article.getCategoryId());
        article.setCategory(category);
        return CommonResult.success(article);
    }

    @PostMapping("/article")
    public CommonResult<?> createArticle(@RequestBody ArticleAddForm form) {
        final Article article = new Article();
        final TokenInfoDTO tokenInfoDTO = TokenInfoContextHolder.get();
        article.setUid(tokenInfoDTO.getUid());
        article.setRole(String.join(",", tokenInfoDTO.getRoles()));
        BeanUtil.copyProperties(form, article);
        final boolean flag = articleService.saveArticle(article);
        return flag ? CommonResult.success("添加成功") : CommonResult.fail("添加失败");
    }

    @PutMapping("/article")
    public CommonResult<?> modifyArticle(@RequestBody ArticleEditForm form) {
        final Article article = new Article();
//        final TokenInfoDTO tokenInfoDTO = TokenInfoContextHolder.get();
//        article.setUid(tokenInfoDTO.getUid());
//        article.setRole(String.join(",", tokenInfoDTO.getRoles()));
        BeanUtil.copyProperties(form, article);
        final boolean flag = articleService.modifyById(article);
        return flag ? CommonResult.success("修改成功") : CommonResult.fail("修改失败");
    }

    @DeleteMapping("/article/{id}")
    public CommonResult<?> removeArticleById(@PathVariable Long id) {
        final boolean flag = articleService.removeArticleById(id);

        return flag ? CommonResult.success("删除成功"): CommonResult.fail("删除失败，存在关联项");
    }

    @DeleteMapping("/article/batch")
    public CommonResult<?> removeBatchArticleByIds(@RequestBody List<Long> ids) {
        final boolean flag = articleService.removeBatchArticleByIds(ids);
        return flag ? CommonResult.success("删除成功"): CommonResult.fail("删除失败，存在关联项");
    }
}
