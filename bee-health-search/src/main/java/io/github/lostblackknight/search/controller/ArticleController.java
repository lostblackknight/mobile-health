package io.github.lostblackknight.search.controller;

import io.github.lostblackknight.model.vo.ArticleSearchParam;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.search.entity.ArticleESModel;
import io.github.lostblackknight.search.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @DeleteMapping("/article/{id}")
    public CommonResult<?> removeArticleById(@PathVariable Long id) {
        articleService.removeArticle(id);
        return CommonResult.success();
    }

    @DeleteMapping("/article")
    public CommonResult<?> removeBatchByIds(@RequestBody List<Long> ids) {
        articleService.removeBatchArticles(ids);
        return CommonResult.success();
    }

    @GetMapping("/article")
    public CommonResult<?> article(ArticleSearchParam param) {
        List<ArticleESModel> models = articleService.getArticleList(param);
        return CommonResult.success(models);
    }
 }
