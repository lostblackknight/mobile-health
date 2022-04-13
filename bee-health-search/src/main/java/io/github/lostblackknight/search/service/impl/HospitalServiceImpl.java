package io.github.lostblackknight.search.service.impl;

import cn.hutool.core.util.StrUtil;
import io.github.lostblackknight.model.vo.HospitalSearchParam;
import io.github.lostblackknight.search.entity.HospitalESModel;
import io.github.lostblackknight.search.repository.HospitalRepository;
import io.github.lostblackknight.search.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
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
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void createIndex() {
        final IndexOperations ops = elasticsearchRestTemplate.indexOps(HospitalESModel.class);
        if (!ops.exists()) {
            log.info("创建索引...");
            ops.create();
        }
    }

    @Override
    public void deleteIndex() {
        final IndexOperations ops = elasticsearchRestTemplate.indexOps(HospitalESModel.class);
        ops.delete();
    }

    @Override
    public void saveHospital(HospitalESModel hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public void saveHospitals(List<HospitalESModel> hospitals) {
        hospitalRepository.saveAll(hospitals);
    }

    @Override
    public Iterable<HospitalESModel> findAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public void removeHospital(String hospitalCode) {
        hospitalRepository.deleteById(hospitalCode);
    }

    @Override
    public List<HospitalESModel> searchHospital(HospitalSearchParam param) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.must(QueryBuilders.matchQuery("hospitalName", param.getHospitalName()));

        if (StrUtil.isNotEmpty(param.getLevelName())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("levelName.keyword", param.getLevelName()));
        }

        if (StrUtil.isNotEmpty(param.getClassName())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("className.keyword", param.getClassName()));
        }

        if (StrUtil.isNotEmpty(param.getHospitalCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hospitalCode.keyword", param.getHospitalCode()));
        }

        if (StrUtil.isNotEmpty(param.getProvince())) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("province", param.getProvince()));
        }

        if (StrUtil.isNotEmpty(param.getCity())) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("city", param.getCity()));
        }

        final NativeSearchQuery query = new NativeSearchQuery(boolQueryBuilder);
        query.setPageable(PageRequest.of(0, 10));
        final SearchHits<HospitalESModel> hits = elasticsearchRestTemplate.search(query, HospitalESModel.class);
        return hits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
