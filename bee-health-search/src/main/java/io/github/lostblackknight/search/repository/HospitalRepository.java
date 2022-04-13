package io.github.lostblackknight.search.repository;

import io.github.lostblackknight.search.entity.HospitalESModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Repository
public interface HospitalRepository extends ElasticsearchRepository<HospitalESModel, String> {
}
