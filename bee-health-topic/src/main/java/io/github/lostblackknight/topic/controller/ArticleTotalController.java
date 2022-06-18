package io.github.lostblackknight.topic.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.lostblackknight.constant.RedisConstant;
import io.github.lostblackknight.model.entity.topic.Article;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.topic.service.ArticleService;
import io.github.lostblackknight.topic.support.TokenInfoContextHolder;
import io.github.lostblackknight.topic.vo.ArticleChartVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ArticleTotalController {

    private final StringRedisTemplate stringRedisTemplate;
    private final ArticleService articleService;

    @GetMapping("/article/total/doctor/read/count")
    public CommonResult<?> getDoctorArticleReadCount() {
        final Long uid = TokenInfoContextHolder.get().getUid();
        if (TokenInfoContextHolder.get().getRoles().contains("admin")) {
            final List<Article> articleList = articleService.list();
            final List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());
            int count = 0;
            for (Long articleId : articleIds) {
                final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_READ_PREFIX + articleId);
                if (hashOps.keys() != null && hashOps.keys().size() != 0) {
                    for (String key : hashOps.keys()) {
                        final String res = hashOps.get(key);
                        count = count + Integer.parseInt(res);
                    }
                }
            }
            return CommonResult.success(count);
        }
        final List<Article> articleList = articleService.list(new QueryWrapper<Article>().eq("uid", uid));
        final List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());
        int count = 0;
        for (Long articleId : articleIds) {
            final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_READ_PREFIX + articleId);
            if (hashOps.keys() != null && hashOps.keys().size() != 0) {
                for (String key : hashOps.keys()) {
                    final String res = hashOps.get(key);
                    count = count + Integer.parseInt(res);
                }
            }
        }
        return CommonResult.success(count);
    }

    @GetMapping("/article/total/doctor/star/count")
    public CommonResult<?> getDoctorArticleStarCount() {
        final Long uid = TokenInfoContextHolder.get().getUid();
        if (TokenInfoContextHolder.get().getRoles().contains("admin")) {
            final List<Article> articleList = articleService.list();
            final List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());
            int count = 0;
            for (Long articleId : articleIds) {
                final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_STAR_PREFIX + articleId);
                if (hashOps.keys() != null && hashOps.keys().size() != 0) {
                    for (String key : hashOps.keys()) {
                        final String res = hashOps.get(key);
                        if (res.split(",")[0].equals("1")) {
                            count = count + 1;
                        }
                    }
                }
            }
            return CommonResult.success(count);
        }
        final List<Article> articleList = articleService.list(new QueryWrapper<Article>().eq("uid", uid));
        final List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());
        int count = 0;
        for (Long articleId : articleIds) {
            final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_STAR_PREFIX + articleId);
            if (hashOps.keys() != null && hashOps.keys().size() != 0) {
                for (String key : hashOps.keys()) {
                    final String res = hashOps.get(key);
                    if (res.split(",")[0].equals("1")) {
                        count = count + 1;
                    }
                }
            }
        }
        return CommonResult.success(count);
    }

    @GetMapping("/article/total/doctor/like/count")
    public CommonResult<?> getDoctorArticleLikeCount() {
        final Long uid = TokenInfoContextHolder.get().getUid();
        if (TokenInfoContextHolder.get().getRoles().contains("admin")) {
            final List<Article> articleList = articleService.list();
            final List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());
            int count = 0;
            for (Long articleId : articleIds) {
                final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_LIKE_PREFIX + articleId);
                if (hashOps.keys() != null && hashOps.keys().size() != 0) {
                    for (String key : hashOps.keys()) {
                        final String res = hashOps.get(key);
                        if (res.split(",")[0].equals("1")) {
                            count = count + 1;
                        }
                    }
                }
            }
            return CommonResult.success(count);
        }
        final List<Article> articleList = articleService.list(new QueryWrapper<Article>().eq("uid", uid));
        final List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());
        int count = 0;
        for (Long articleId : articleIds) {
            final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_LIKE_PREFIX + articleId);
            if (hashOps.keys() != null && hashOps.keys().size() != 0) {
                for (String key : hashOps.keys()) {
                    final String res = hashOps.get(key);
                    if (res.split(",")[0].equals("1")) {
                        count = count + 1;
                    }
                }
            }
        }
        return CommonResult.success(count);
    }

    @GetMapping("/article/total/doctor/read/chart")
    public CommonResult<?> getDoctorArticleReadChart() {
        final Long uid = TokenInfoContextHolder.get().getUid();
        final List<Article> articleList;
        if (TokenInfoContextHolder.get().getRoles().contains("admin")) {
            articleList = articleService.list();
        } else  {
            articleList = articleService.list(new QueryWrapper<Article>().eq("uid", uid));
        }
        final List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());
        final ArrayList<List<ArticleChartVO>> lists = new ArrayList<>();
        for (Long articleId : articleIds) {
            final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_READ_PREFIX + articleId + ":total");
            final ArrayList<ArticleChartVO> list = new ArrayList<>();
            if (hashOps.keys() != null && hashOps.keys().size() != 0) {
                for (String key : hashOps.keys()) {
                    final ArticleChartVO vo = new ArticleChartVO();
                    vo.setDate(key);
                    vo.setCount(Integer.valueOf(hashOps.get(key)));
                    list.add(vo);
                }
            }
            lists.add(list);
        }

        final String from0 = DateUtil.format(DateUtil.offsetDay(new Date(), -6).toJdkDate(), "yyyy-MM-dd");
        final String from1 = DateUtil.format(DateUtil.offsetDay(new Date(), -5).toJdkDate(), "yyyy-MM-dd");
        final String from2 = DateUtil.format(DateUtil.offsetDay(new Date(), -4).toJdkDate(), "yyyy-MM-dd");
        final String from3 = DateUtil.format(DateUtil.offsetDay(new Date(), -3).toJdkDate(), "yyyy-MM-dd");
        final String from4 = DateUtil.format(DateUtil.offsetDay(new Date(), -2).toJdkDate(), "yyyy-MM-dd");
        final String from5 = DateUtil.format(DateUtil.offsetDay(new Date(), -1).toJdkDate(), "yyyy-MM-dd");
        final String from6 = DateUtil.format(DateUtil.offsetDay(new Date(), 0).toJdkDate(), "yyyy-MM-dd");

        final ArticleChartVO vo0 = new ArticleChartVO();
        vo0.setDate(from0);
        final ArticleChartVO vo1 = new ArticleChartVO();
        vo1.setDate(from1);
        final ArticleChartVO vo2 = new ArticleChartVO();
        vo2.setDate(from2);
        final ArticleChartVO vo3 = new ArticleChartVO();
        vo3.setDate(from3);
        final ArticleChartVO vo4 = new ArticleChartVO();
        vo4.setDate(from4);
        final ArticleChartVO vo5 = new ArticleChartVO();
        vo5.setDate(from5);
        final ArticleChartVO vo6 = new ArticleChartVO();
        vo6.setDate(from6);

        final ArrayList<ArticleChartVO> res = new ArrayList<>();

        for (List<ArticleChartVO> article : lists) {
            for (ArticleChartVO articleChartVO : article) {
                if (articleChartVO.getDate().equals(from0)) {
                    vo0.setCount(vo0.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from1)) {
                    vo1.setCount(vo1.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from2)) {
                    vo2.setCount(vo2.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from3)) {
                    vo3.setCount(vo3.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from4)) {
                    vo4.setCount(vo4.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from5)) {
                    vo5.setCount(vo5.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from6)) {
                    vo6.setCount(vo6.getCount() + articleChartVO.getCount());
                }
            }
        }
        res.addAll(List.of(vo0, vo1, vo2, vo3, vo4, vo5, vo6));
        return CommonResult.success(res);
    }

    @GetMapping("/article/total/doctor/like/chart")
    public CommonResult<?> getDoctorArticleLikeChart() {
        final Long uid = TokenInfoContextHolder.get().getUid();
        final List<Article> articleList;
        if (TokenInfoContextHolder.get().getRoles().contains("admin")) {
            articleList = articleService.list();
        } else  {
            articleList = articleService.list(new QueryWrapper<Article>().eq("uid", uid));
        }
        final List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());
        final ArrayList<List<ArticleChartVO>> lists = new ArrayList<>();
        for (Long articleId : articleIds) {
            final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_LIKE_PREFIX + articleId + ":total");
            final ArrayList<ArticleChartVO> list = new ArrayList<>();
            if (hashOps.keys() != null && hashOps.keys().size() != 0) {
                for (String key : hashOps.keys()) {
                    final ArticleChartVO vo = new ArticleChartVO();
                    vo.setDate(key);
                    vo.setCount(Integer.valueOf(hashOps.get(key)));
                    list.add(vo);
                }
            }
            lists.add(list);
        }

        final String from0 = DateUtil.format(DateUtil.offsetDay(new Date(), -6).toJdkDate(), "yyyy-MM-dd");
        final String from1 = DateUtil.format(DateUtil.offsetDay(new Date(), -5).toJdkDate(), "yyyy-MM-dd");
        final String from2 = DateUtil.format(DateUtil.offsetDay(new Date(), -4).toJdkDate(), "yyyy-MM-dd");
        final String from3 = DateUtil.format(DateUtil.offsetDay(new Date(), -3).toJdkDate(), "yyyy-MM-dd");
        final String from4 = DateUtil.format(DateUtil.offsetDay(new Date(), -2).toJdkDate(), "yyyy-MM-dd");
        final String from5 = DateUtil.format(DateUtil.offsetDay(new Date(), -1).toJdkDate(), "yyyy-MM-dd");
        final String from6 = DateUtil.format(DateUtil.offsetDay(new Date(), 0).toJdkDate(), "yyyy-MM-dd");

        final ArticleChartVO vo0 = new ArticleChartVO();
        vo0.setDate(from0);
        final ArticleChartVO vo1 = new ArticleChartVO();
        vo1.setDate(from1);
        final ArticleChartVO vo2 = new ArticleChartVO();
        vo2.setDate(from2);
        final ArticleChartVO vo3 = new ArticleChartVO();
        vo3.setDate(from3);
        final ArticleChartVO vo4 = new ArticleChartVO();
        vo4.setDate(from4);
        final ArticleChartVO vo5 = new ArticleChartVO();
        vo5.setDate(from5);
        final ArticleChartVO vo6 = new ArticleChartVO();
        vo6.setDate(from6);

        final ArrayList<ArticleChartVO> res = new ArrayList<>();

        for (List<ArticleChartVO> article : lists) {
            for (ArticleChartVO articleChartVO : article) {
                if (articleChartVO.getDate().equals(from0)) {
                    vo0.setCount(vo0.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from1)) {
                    vo1.setCount(vo1.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from2)) {
                    vo2.setCount(vo2.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from3)) {
                    vo3.setCount(vo3.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from4)) {
                    vo4.setCount(vo4.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from5)) {
                    vo5.setCount(vo5.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from6)) {
                    vo6.setCount(vo6.getCount() + articleChartVO.getCount());
                }
            }
        }
        res.addAll(List.of(vo0, vo1, vo2, vo3, vo4, vo5, vo6));
        return CommonResult.success(res);
    }

    @GetMapping("/article/total/doctor/star/chart")
    public CommonResult<?> getDoctorArticleStarChart() {
        final Long uid = TokenInfoContextHolder.get().getUid();
        final List<Article> articleList;
        if (TokenInfoContextHolder.get().getRoles().contains("admin")) {
            articleList = articleService.list();
        } else  {
            articleList = articleService.list(new QueryWrapper<Article>().eq("uid", uid));
        }
        final List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());
        final ArrayList<List<ArticleChartVO>> lists = new ArrayList<>();
        for (Long articleId : articleIds) {
            final BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(RedisConstant.ARTICLE_STAR_PREFIX + articleId + ":total");
            final ArrayList<ArticleChartVO> list = new ArrayList<>();
            if (hashOps.keys() != null && hashOps.keys().size() != 0) {
                for (String key : hashOps.keys()) {
                    final ArticleChartVO vo = new ArticleChartVO();
                    vo.setDate(key);
                    vo.setCount(Integer.valueOf(hashOps.get(key)));
                    list.add(vo);
                }
            }
            lists.add(list);
        }

        final String from0 = DateUtil.format(DateUtil.offsetDay(new Date(), -6).toJdkDate(), "yyyy-MM-dd");
        final String from1 = DateUtil.format(DateUtil.offsetDay(new Date(), -5).toJdkDate(), "yyyy-MM-dd");
        final String from2 = DateUtil.format(DateUtil.offsetDay(new Date(), -4).toJdkDate(), "yyyy-MM-dd");
        final String from3 = DateUtil.format(DateUtil.offsetDay(new Date(), -3).toJdkDate(), "yyyy-MM-dd");
        final String from4 = DateUtil.format(DateUtil.offsetDay(new Date(), -2).toJdkDate(), "yyyy-MM-dd");
        final String from5 = DateUtil.format(DateUtil.offsetDay(new Date(), -1).toJdkDate(), "yyyy-MM-dd");
        final String from6 = DateUtil.format(DateUtil.offsetDay(new Date(), 0).toJdkDate(), "yyyy-MM-dd");

        final ArticleChartVO vo0 = new ArticleChartVO();
        vo0.setDate(from0);
        final ArticleChartVO vo1 = new ArticleChartVO();
        vo1.setDate(from1);
        final ArticleChartVO vo2 = new ArticleChartVO();
        vo2.setDate(from2);
        final ArticleChartVO vo3 = new ArticleChartVO();
        vo3.setDate(from3);
        final ArticleChartVO vo4 = new ArticleChartVO();
        vo4.setDate(from4);
        final ArticleChartVO vo5 = new ArticleChartVO();
        vo5.setDate(from5);
        final ArticleChartVO vo6 = new ArticleChartVO();
        vo6.setDate(from6);

        final ArrayList<ArticleChartVO> res = new ArrayList<>();

        for (List<ArticleChartVO> article : lists) {
            for (ArticleChartVO articleChartVO : article) {
                if (articleChartVO.getDate().equals(from0)) {
                    vo0.setCount(vo0.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from1)) {
                    vo1.setCount(vo1.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from2)) {
                    vo2.setCount(vo2.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from3)) {
                    vo3.setCount(vo3.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from4)) {
                    vo4.setCount(vo4.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from5)) {
                    vo5.setCount(vo5.getCount() + articleChartVO.getCount());
                } else if (articleChartVO.getDate().equals(from6)) {
                    vo6.setCount(vo6.getCount() + articleChartVO.getCount());
                }
            }
        }
        res.addAll(List.of(vo0, vo1, vo2, vo3, vo4, vo5, vo6));
        return CommonResult.success(res);
    }

}
