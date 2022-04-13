package io.github.lostblackknight.search.repository;

import io.github.lostblackknight.search.entity.DeptESModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Repository
public interface DeptRepository extends ElasticsearchRepository<DeptESModel, String> {
}
