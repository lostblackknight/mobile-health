package io.github.lostblackknight.search.repository;

import io.github.lostblackknight.search.entity.ScheduleESModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Repository
public interface ScheduleRepository extends ElasticsearchRepository<ScheduleESModel, String> {
}
