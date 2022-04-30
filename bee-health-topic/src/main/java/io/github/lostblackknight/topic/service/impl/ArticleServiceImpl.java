package io.github.lostblackknight.topic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.model.entity.admin.User;
import io.github.lostblackknight.model.entity.member.Member;
import io.github.lostblackknight.model.entity.search.ArticleES;
import io.github.lostblackknight.model.entity.search.SuggestES;
import io.github.lostblackknight.model.entity.topic.Article;
import io.github.lostblackknight.model.entity.topic.Category;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.topic.client.AdminClient;
import io.github.lostblackknight.topic.client.MemberClient;
import io.github.lostblackknight.topic.client.SearchClient;
import io.github.lostblackknight.topic.service.ArticleService;
import io.github.lostblackknight.topic.mapper.ArticleMapper;
import io.github.lostblackknight.topic.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chensixiang
 * @description 针对表【t_article】的数据库操作Service实现
 * @createDate 2022-04-23 20:08:00
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    private final SearchClient searchClient;

    private final AdminClient adminClient;

    private final MemberClient memberClient;

    @Resource
    private CategoryService categoryService;

    @Override
    public long getCountByCategoryId(Long id) {
        return baseMapper.selectCount(new QueryWrapper<Article>().eq("category_id", id));
    }

    @Override
    public boolean saveArticle(Article article) {
        final boolean flag = save(article);
        // 上传到ES

        final SuggestES suggestES = new SuggestES();
        suggestES.setId(String.valueOf(article.getId()));
        suggestES.setSuggest(article.getTitle());

        searchClient.uploadSuggestToES(suggestES);

        final ArticleES articleES = new ArticleES();
        BeanUtil.copyProperties(article, articleES);

        articleES.setCollectionCount(0L);
        articleES.setLikeCount(0L);
        articleES.setReadCount(0L);
        // 头像 分类名
        final Category category = categoryService.getById(article.getCategoryId());
        articleES.setCategoryName(category.getName());

        if (article.getRole().contains("admin")) {
            final CommonResult<User> result = adminClient.getUserById(article.getUid());
            if (result.getCode() == 1) {
                articleES.setAvatar(result.getData().getAvatar());
            }
        }
        if (article.getRole().contains("doctor")) {
            final CommonResult<Member> result = memberClient.getMemberById(article.getUid());
            if (result.getCode() == 1) {
                articleES.setAvatar(result.getData().getAvatar());
            }
        }
        searchClient.uploadArticleToES(articleES);
        return flag;
    }

    @Override
    public boolean modifyById(Article article) {
        final Article article1 = baseMapper.selectById(article.getId());
        final boolean flag = updateById(article);
        // 上传到ES
        final SuggestES suggestES = new SuggestES();
        suggestES.setId(String.valueOf(article.getId()));
        suggestES.setSuggest(article.getTitle());
        searchClient.uploadSuggestToES(suggestES);

        final ArticleES articleES = new ArticleES();
        BeanUtil.copyProperties(article, articleES);
        articleES.setCollectionCount(article.getCollectionCount() == null ? article1.getCollectionCount() : article.getCollectionCount());
        articleES.setLikeCount(article.getLikeCount() == null ? article1.getLikeCount() : article.getCollectionCount());
        articleES.setReadCount(article.getReadCount() == null ? article1.getReadCount() : article.getReadCount());
        // 头像 分类名
        final Category category = categoryService.getById(article1.getCategoryId());
        articleES.setCategoryName(category.getName());

        if (article1.getRole().contains("admin")) {
            final CommonResult<User> result = adminClient.getUserById(article1.getUid());
            if (result.getCode() == 1) {
                articleES.setAvatar(result.getData().getAvatar());
            }
        }
        if (article1.getRole().contains("doctor")) {
            final CommonResult<Member> result = memberClient.getMemberById(article1.getUid());
            if (result.getCode() == 1) {
                articleES.setAvatar(result.getData().getAvatar());
            }
        }
        searchClient.uploadArticleToES(articleES);
        return flag;
    }

    @Override
    public boolean removeArticleById(Long id) {
        final boolean flag = removeById(id);
        searchClient.removeArticleById(id);
        searchClient.remove(String.valueOf(id));
        return flag;
    }

    @Override
    public boolean removeBatchArticleByIds(List<Long> ids) {
        final boolean flag = removeBatchByIds(ids);
        searchClient.removeBatchByIds(ids);
        ids.forEach(aLong -> searchClient.remove(String.valueOf(aLong)));
        return flag;
    }
}




