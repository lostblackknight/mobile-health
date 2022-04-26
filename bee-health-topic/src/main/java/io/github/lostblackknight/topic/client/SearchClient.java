package io.github.lostblackknight.topic.client;

import io.github.lostblackknight.model.entity.search.ArticleES;
import io.github.lostblackknight.model.entity.search.SuggestES;
import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@FeignClient("bee-health-search")
public interface SearchClient {

    @PostMapping("/articles/upload")
    CommonResult<?> uploadArticleToES(@RequestBody ArticleES articleES);

    @DeleteMapping("/article/{id}")
    CommonResult<?> removeArticleById(@PathVariable Long id);

    @DeleteMapping("/article")
    CommonResult<?> removeBatchByIds(@RequestBody List<Long> ids);

    @PostMapping("/suggests/upload")
    CommonResult<?> uploadSuggestToES(@RequestBody SuggestES suggestES);

    @DeleteMapping("/suggest/{id}")
    CommonResult<?> remove(@PathVariable String id);
}
