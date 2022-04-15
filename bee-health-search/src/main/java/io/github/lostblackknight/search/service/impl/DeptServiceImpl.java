package io.github.lostblackknight.search.service.impl;

import cn.hutool.core.util.StrUtil;
import io.github.lostblackknight.model.dto.DeptESDTO;
import io.github.lostblackknight.model.vo.DeptSearchParam;
import io.github.lostblackknight.search.entity.DeptESModel;
import io.github.lostblackknight.search.repository.DeptRepository;
import io.github.lostblackknight.search.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void createIndex() {

    }

    @Override
    public void deleteIndex() {

    }

    @Override
    public void saveDept(DeptESModel dept) {
        deptRepository.save(dept);
    }

    @Override
    public void saveDepts(List<DeptESModel> depts) {
        deptRepository.saveAll(depts);
    }

    @Override
    public Iterable<DeptESModel> findAll() {
        return null;
    }

    @Override
    public void removeDept(String deptCode) {

    }

    @Override
    public List<DeptESModel> searchDept(DeptSearchParam param) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (StrUtil.isNotEmpty(param.getDeptName())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("deptName", param.getDeptName()));
        }

        if (StrUtil.isNotEmpty(param.getHospitalName())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("hospitalName", param.getHospitalName()));
        }

        if (StrUtil.isNotEmpty(param.getDeptCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("deptCode", param.getDeptCode()));
        }

        if (StrUtil.isNotEmpty(param.getHospitalCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hospitalCode", param.getHospitalCode()));
        }

        if (StrUtil.isNotEmpty(param.getProvince())) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("province", param.getProvince()));
        }

        if (StrUtil.isNotEmpty(param.getCity())) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("city", param.getCity()));
        }

        final NativeSearchQuery query = new NativeSearchQuery(boolQueryBuilder);
        query.setPageable(PageRequest.of(param.getPageNum() - 1, param.getPageSize()));

        final SearchHits<DeptESModel> searchHits = elasticsearchRestTemplate.search(query, DeptESModel.class);
        return searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public List<DeptESDTO> searchDeptClassList(String hospitalCode) {
        final TermQueryBuilder termQuery = QueryBuilders.termQuery("hospitalCode", hospitalCode);

        final TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("classCode_agg").field("classCode");

        final NativeSearchQuery query = new NativeSearchQuery(termQuery);

        query.addAggregation(aggregationBuilder);
        query.setPageable(PageRequest.of(0, 1000));
        query.addSort(Sort.by("deptCode"));

        final SearchHits<DeptESModel> searchHits = elasticsearchRestTemplate.search(query, DeptESModel.class);


        final List<SearchHit<DeptESModel>> searchHitList = searchHits.getSearchHits();
        final List<DeptESModel> deptESModels = searchHitList.stream().map(SearchHit::getContent).collect(Collectors.toList());

        final ParsedStringTerms aggregation = searchHits.getAggregations().get("classCode_agg");

        final List<String> classCodeList = aggregation.getBuckets().stream().map(bucket -> (String) bucket.getKey()).collect(Collectors.toList());

        final List<DeptESDTO> deptDTOList = classCodeList.stream().map(classCode -> {
            final DeptESDTO deptDTO = new DeptESDTO();
            deptDTO.setId(classCode);
            final ArrayList<DeptESDTO> deptDTOS = new ArrayList<>();

            deptESModels.stream().filter(deptESModel -> Objects.equals(deptESModel.getClassCode(), classCode))
                    .forEach(deptESModel -> {
                        if (deptDTO.getText() == null) {
                            deptDTO.setText(deptESModel.getClassName());
                        }
                        final DeptESDTO dto = new DeptESDTO();
                        dto.setId(deptESModel.getDeptCode());
                        dto.setText(deptESModel.getDeptName());
                        dto.setChildren(null);
                        deptDTOS.add(dto);
                    });
            deptDTO.setChildren(deptDTOS);
            return deptDTO;
        }).collect(Collectors.toList());

        return deptDTOList;
    }

}
