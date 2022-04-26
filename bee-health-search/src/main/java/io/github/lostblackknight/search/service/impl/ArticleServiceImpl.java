package io.github.lostblackknight.search.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.lostblackknight.model.vo.ArticleSearchParam;
import io.github.lostblackknight.search.entity.ArticleESModel;
import io.github.lostblackknight.search.entity.DeptESModel;
import io.github.lostblackknight.search.repository.ArticleRepository;
import io.github.lostblackknight.search.repository.DeptRepository;
import io.github.lostblackknight.search.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void createIndex() {

    }

    @Override
    public void deleteIndex() {
        elasticsearchRestTemplate.indexOps(ArticleESModel.class).delete();
    }

    @Override
    public void saveArticle(ArticleESModel article) {
        articleRepository.save(article);
    }

    @Override
    public void saveArticles(List<ArticleESModel> articles) {
        articleRepository.saveAll(articles);
    }

    @Override
    public Iterable<ArticleESModel> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public void removeArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void removeBatchArticles(List<Long> ids) {
        ids.forEach(articleRepository::deleteById);
    }

    @Override
    public List<ArticleESModel> getArticleList(ArticleSearchParam param) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (StrUtil.isNotEmpty(param.getTitle())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("title", param.getTitle()));
            if (param.getTitle().equals("文章")) {
                final MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
                final NativeSearchQuery query = new NativeSearchQuery(matchAllQueryBuilder);
                query.setPageable(PageRequest.of(param.getPageNum() - 1, param.getPageSize()));

                final SearchHits<ArticleESModel> searchHits = elasticsearchRestTemplate.search(query, ArticleESModel.class);
                return searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
            }
        }

        if (StrUtil.isNotEmpty(param.getAuthor())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("author", param.getAuthor()));
        }

        if (StrUtil.isNotEmpty(param.getCategoryName())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("categoryName", param.getCategoryName()));
        }

        if (ObjectUtil.isNotEmpty(param.getCategoryId())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("categoryId", param.getCategoryId()));
        }

        if (ObjectUtil.isNotEmpty(param.getId())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("id", param.getId()));
        }

        if (ObjectUtil.isNotEmpty(param.getUid())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("uid", param.getUid()));
        }

        final NativeSearchQuery query = new NativeSearchQuery(boolQueryBuilder);
        query.setPageable(PageRequest.of(param.getPageNum() - 1, param.getPageSize()));

        final SearchHits<ArticleESModel> searchHits = elasticsearchRestTemplate.search(query, ArticleESModel.class);
        return searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
