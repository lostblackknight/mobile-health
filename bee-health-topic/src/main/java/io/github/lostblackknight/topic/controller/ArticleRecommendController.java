package io.github.lostblackknight.topic.controller;

import cn.hutool.core.date.DateUtil;
import io.github.lostblackknight.constant.RedisConstant;
import io.github.lostblackknight.model.entity.topic.Article;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.topic.service.ArticleService;
import io.github.lostblackknight.topic.support.TokenInfoContextHolder;
import io.github.lostblackknight.topic.vo.ArticleReadVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class ArticleRecommendController {

    private final StringRedisTemplate stringRedisTemplate;
    private final ArticleService articleService;

    @GetMapping("/article/like/{articleId}")
    public CommonResult<?> isLike(@PathVariable Long articleId) {
        final BoundHashOperations<String, String , String > hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_LIKE_PREFIX + articleId);
        final Long userId = TokenInfoContextHolder.get().getUid();
        final String like = hashOps.get(String.valueOf(userId));
        if (like == null || like.split(",")[0].equals("0")) {
            return CommonResult.success(false);
        } else {
            return CommonResult.success(true);
        }
    }

    @GetMapping("/article/star/{articleId}")
    public CommonResult<?> isStar(@PathVariable Long articleId) {
        final BoundHashOperations<String, String , String > hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_STAR_PREFIX + articleId);
        final Long userId = TokenInfoContextHolder.get().getUid();
        final String like = hashOps.get(String.valueOf(userId));
        if (like == null || like.split(",")[0].equals("0")) {
            return CommonResult.success(false);
        } else {
            return CommonResult.success(true);
        }
    }

    @PutMapping("/article/confirm/like/{articleId}")
    public CommonResult<?> confirmLike(@PathVariable Long articleId) {
        final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_LIKE_PREFIX + articleId);
        final Long userId = TokenInfoContextHolder.get().getUid();
        final String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        hashOps.put(String.valueOf(userId), String.valueOf(1) + "," + date);
        final String date1 = DateUtil.format(new Date(), "yyyy-MM-dd");
        final BoundHashOperations<String, Object, Object> hashOps1 = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_LIKE_PREFIX + articleId + ":total");
        hashOps1.increment(date1, 1L);
        // 添加文章列表
        final BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps(RedisConstant.USER_LIKE_ARTICLE_PREFIX + userId);
        listOps.leftPush(String.valueOf(articleId));
        // 更新数据库
        // 更新ES
        final Article article = articleService.getById(articleId);
        article.setLikeCount(article.getLikeCount() + 1);
        articleService.modifyById(article);
        return CommonResult.success();
    }

    @PutMapping("/article/cancel/like/{articleId}")
    public CommonResult<?> cancelLike(@PathVariable Long articleId) {
        final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_LIKE_PREFIX + articleId);
        final Long userId = TokenInfoContextHolder.get().getUid();
        final String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        hashOps.put(String.valueOf(userId), String.valueOf(0) + "," + date);
        final String date1 = DateUtil.format(new Date(), "yyyy-MM-dd");
        final BoundHashOperations<String, Object, Object> hashOps1 = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_LIKE_PREFIX + articleId + ":total");
        hashOps1.increment(date1, -1L);
        // 移除文章列表
        final BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps(RedisConstant.USER_LIKE_ARTICLE_PREFIX + userId);
        listOps.remove(1, String.valueOf(articleId));
        // 更新数据库
        // 更新ES
        final Article article = articleService.getById(articleId);
        article.setLikeCount(article.getLikeCount() - 1);
        articleService.modifyById(article);
        return CommonResult.success();
    }

    @PutMapping("/article/confirm/star/{articleId}")
    public CommonResult<?> confirmStar(@PathVariable Long articleId) {
        final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_STAR_PREFIX + articleId);
        final Long userId = TokenInfoContextHolder.get().getUid();
        final String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        hashOps.put(String.valueOf(userId), String.valueOf(1) + "," + date);
        final String date1 = DateUtil.format(new Date(), "yyyy-MM-dd");
        final BoundHashOperations<String, Object, Object> hashOps1 = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_STAR_PREFIX + articleId + ":total");
        hashOps1.increment(date1, 1L);
        // 添加文章列表
        final BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps(RedisConstant.USER_STAR_ARTICLE_PREFIX + userId);
        listOps.leftPush(String.valueOf(articleId));
        // 更新数据库
        // 更新ES
        final Article article = articleService.getById(articleId);
        article.setCollectionCount(article.getCollectionCount() + 1);
        articleService.modifyById(article);
        return CommonResult.success();
    }

    @PutMapping("/article/cancel/star/{articleId}")
    public CommonResult<?> cancelStar(@PathVariable Long articleId) {
        final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_STAR_PREFIX + articleId);
        final Long userId = TokenInfoContextHolder.get().getUid();
        final String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        hashOps.put(String.valueOf(userId), String.valueOf(0) + "," + date);
        final String date1 = DateUtil.format(new Date(), "yyyy-MM-dd");
        final BoundHashOperations<String, Object, Object> hashOps1 = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_STAR_PREFIX + articleId + ":total");
        hashOps1.increment(date1, -1L);
        // 移除文章列表
        final BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps(RedisConstant.USER_STAR_ARTICLE_PREFIX + userId);
        listOps.remove(1, String.valueOf(articleId));
        // 更新数据库
        // 更新ES
        final Article article = articleService.getById(articleId);
        article.setCollectionCount(article.getCollectionCount() - 1);
        articleService.modifyById(article);
        return CommonResult.success();
    }

    @PutMapping("/article/confirm/read/{articleId}")
    public CommonResult<?> confirmRead(@PathVariable Long articleId) {
        final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_READ_PREFIX + articleId);
        final Long userId = TokenInfoContextHolder.get().getUid();
        hashOps.increment(String.valueOf(userId), 1L);
        final String date1 = DateUtil.format(new Date(), "yyyy-MM-dd");
        final BoundHashOperations<String, Object, Object> hashOps1 = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_READ_PREFIX + articleId + ":total");
        hashOps1.increment(date1, 1L);
        // 添加文章列表
        final BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps(RedisConstant.USER_READ_ARTICLE_PREFIX + userId);
        final String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        listOps.leftPush(articleId + "," + date);
        // 更新数据库
        // 更新ES
        final Article article = articleService.getById(articleId);
        long count = 0;
        if (hashOps.get(String.valueOf(userId)) != null) {
            count = Long.parseLong(hashOps.get(String.valueOf(userId)));
        }
        article.setReadCount(count);
        articleService.modifyById(article);
        return CommonResult.success();
    }

    @GetMapping("/article/user/like/list")
    public CommonResult<?> getUserArticleLikeList() {
        final Long userId = TokenInfoContextHolder.get().getUid();
        final BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps(RedisConstant.USER_LIKE_ARTICLE_PREFIX + userId);
        List<String> list = listOps.range(0, -1);
        if (list == null) {
            list = new ArrayList<>();
        }
        return CommonResult.success(list);
    }

    @GetMapping("/article/user/star/list")
    public CommonResult<?> getUserArticleStarList() {
        final Long userId = TokenInfoContextHolder.get().getUid();
        final BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps(RedisConstant.USER_STAR_ARTICLE_PREFIX + userId);
        List<String> list = listOps.range(0, -1);
        if (list == null) {
            list = new ArrayList<>();
        }
        return CommonResult.success(list);
    }

    @GetMapping("/article/user/read/list")
    public CommonResult<?> getUserArticleReadList() {
        final Long userId = TokenInfoContextHolder.get().getUid();
        final BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps(RedisConstant.USER_READ_ARTICLE_PREFIX + userId);
        List<String> list = listOps.range(0, -1);
        if (list == null) {
            list = new ArrayList<>();
        }
        final List<ArticleReadVo> collect = list.stream().map(item -> {
            final ArticleReadVo vo = new ArticleReadVo();
            vo.setId(Long.valueOf(item.split(",")[0]));
            vo.setDate(item.split(",")[1]);
            return vo;
        }).collect(Collectors.toList());
        return CommonResult.success(collect);
    }
}
