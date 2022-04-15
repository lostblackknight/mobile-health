package io.github.lostblackknight.search.repository;

import io.github.lostblackknight.search.entity.SuggestESModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Repository
public interface SuggestRepository extends ElasticsearchRepository<SuggestESModel, String> {
}
