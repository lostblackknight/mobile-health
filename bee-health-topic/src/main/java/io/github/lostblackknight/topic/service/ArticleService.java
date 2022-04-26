package io.github.lostblackknight.topic.service;

import io.github.lostblackknight.model.entity.topic.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_article】的数据库操作Service
* @createDate 2022-04-23 20:08:00
*/
public interface ArticleService extends IService<Article> {

    long getCountByCategoryId(Long id);

    boolean saveArticle(Article article);

    boolean modifyById(Article article);

    boolean removeArticleById(Long id);

    boolean removeBatchArticleByIds(List<Long> ids);
}
