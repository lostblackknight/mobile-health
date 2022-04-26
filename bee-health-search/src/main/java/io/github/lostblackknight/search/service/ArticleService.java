package io.github.lostblackknight.search.service;

import io.github.lostblackknight.model.vo.ArticleSearchParam;
import io.github.lostblackknight.search.entity.ArticleESModel;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public interface ArticleService {

    void createIndex();

    void deleteIndex();

    void saveArticle(ArticleESModel article);

    void saveArticles(List<ArticleESModel> articles);

    Iterable<ArticleESModel> findAll();

    void removeArticle(Long id);

    void removeBatchArticles(List<Long> ids);

    List<ArticleESModel> getArticleList(ArticleSearchParam param);
}
