package io.github.lostblackknight.search.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.lostblackknight.model.dto.DoctorDTO;
import io.github.lostblackknight.model.dto.ScheduleDateDTO;
import io.github.lostblackknight.model.vo.ScheduleDateParam;
import io.github.lostblackknight.model.vo.ScheduleDoctorParam;
import io.github.lostblackknight.model.vo.ScheduleParam;
import io.github.lostblackknight.search.entity.DeptESModel;
import io.github.lostblackknight.search.entity.ScheduleESModel;
import io.github.lostblackknight.search.repository.ScheduleRepository;
import io.github.lostblackknight.search.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedDoubleTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedMax;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void createIndex() {

    }

    @Override
    public void deleteIndex() {

    }

    @Override
    public void saveSchedule(ScheduleESModel schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void saveSchedules(List<ScheduleESModel> schedules) {
        scheduleRepository.saveAll(schedules);
    }

    @Override
    public Iterable<ScheduleESModel> findAll() {
        return null;
    }

    @Override
    public void removeHospital(String scheduleId) {

    }

    @Override
    public List<ScheduleESModel> searchSchedule(ScheduleParam param) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (StrUtil.isNotEmpty(param.getDeptCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("deptCode", param.getDeptCode()));
        }

        if (StrUtil.isNotEmpty(param.getHospitalCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hospitalCode", param.getHospitalCode()));
        }

        if (StrUtil.isNotEmpty(param.getDate())) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("date", param.getDate()));
        }

        if (StrUtil.isNotEmpty(param.getScheduleId())) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("scheduleId", param.getScheduleId()));
        }

        final NativeSearchQuery query = new NativeSearchQuery(boolQueryBuilder);

        query.setPageable(PageRequest.of(param.getPageNum() - 1, param.getPageSize()));

        query.addSort(Sort.by("date"));

        final SearchHits<ScheduleESModel> searchHits = elasticsearchRestTemplate.search(query, ScheduleESModel.class);
        return searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDateDTO> getScheduleDateList(ScheduleDateParam param) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (StrUtil.isNotEmpty(param.getDeptCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("deptCode", param.getDeptCode()));
        }

        if (StrUtil.isNotEmpty(param.getHospitalCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hospitalCode", param.getHospitalCode()));
        }

        final TermsAggregationBuilder dateAgg = AggregationBuilders.terms("date_agg").field("date").order(BucketOrder.key(true));

        dateAgg.subAggregation(AggregationBuilders.terms("week_agg").field("week.keyword"));
        dateAgg.subAggregation(AggregationBuilders.max("state_agg").field("yuYueState"));

        final NativeSearchQuery query = new NativeSearchQuery(boolQueryBuilder);

        query.addAggregation(dateAgg);

        query.setPageable(PageRequest.of(param.getPageNum() - 1, param.getPageSize()));

        query.addSort(Sort.by("date"));

        final SearchHits<ScheduleESModel> searchHits = elasticsearchRestTemplate.search(query, ScheduleESModel.class);

        final ParsedLongTerms date_agg = searchHits.getAggregations().get("date_agg");

        final List<ScheduleDateDTO> dtoList = date_agg.getBuckets().stream().map(bucket -> {
            final ScheduleDateDTO dto = new ScheduleDateDTO();
            dto.setDate(new Date((Long) bucket.getKey()));
            final ParsedStringTerms week_agg = bucket.getAggregations().get("week_agg");
            dto.setWeek((String) week_agg.getBuckets().get(0).getKey());
            final ParsedMax state_agg = bucket.getAggregations().get("state_agg");
            dto.setStatus((int) state_agg.value());
            return dto;
        }).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public List<DoctorDTO> getDoctorList(ScheduleDoctorParam param) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (StrUtil.isNotEmpty(param.getDeptCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("deptCode", param.getDeptCode()));
        }

        if (StrUtil.isNotEmpty(param.getHospitalCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hospitalCode", param.getHospitalCode()));
        }

        if (StrUtil.isNotEmpty(param.getDate())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("date", param.getDate()));
        }

        final TermsAggregationBuilder doctorNameAgg = AggregationBuilders.terms("doctorName_agg").field("doctorName.keyword");

        doctorNameAgg.subAggregation(AggregationBuilders.terms("expert_agg").field("expert.keyword"));
        doctorNameAgg.subAggregation(AggregationBuilders.terms("illNameList_agg").field("illNameList.keyword"));
        doctorNameAgg.subAggregation(AggregationBuilders.terms("icon_agg").field("icon.keyword"));
        doctorNameAgg.subAggregation(AggregationBuilders.terms("levelName_agg").field("levelName.keyword"));
        doctorNameAgg.subAggregation(AggregationBuilders.terms("hospitalName_agg").field("hospitalName.keyword"));
        doctorNameAgg.subAggregation(AggregationBuilders.terms("deptName_agg").field("deptName.keyword"));
        doctorNameAgg.subAggregation(AggregationBuilders.terms("hospitalCode_agg").field("hospitalCode.keyword"));
        doctorNameAgg.subAggregation(AggregationBuilders.terms("deptCode_agg").field("deptCode.keyword"));
        doctorNameAgg.subAggregation(AggregationBuilders.max("status_agg").field("yuYueState"));


        final NativeSearchQuery query = new NativeSearchQuery(boolQueryBuilder);

        query.addAggregation(doctorNameAgg);

        query.setPageable(PageRequest.of(param.getPageNum() - 1, param.getPageSize()));

        final SearchHits<ScheduleESModel> searchHits = elasticsearchRestTemplate.search(query, ScheduleESModel.class);

        final Aggregations aggregations = searchHits.getAggregations();

        final ParsedStringTerms doctorName_agg = aggregations.get("doctorName_agg");
        final List<DoctorDTO> collect = doctorName_agg.getBuckets().stream().map(bucket -> {
            final DoctorDTO dto = new DoctorDTO();
            dto.setDoctorName((String) bucket.getKey());
            final ParsedStringTerms icon_agg = bucket.getAggregations().get("icon_agg");
            dto.setIcon((String) icon_agg.getBuckets().get(0).getKey());
            final ParsedStringTerms expert_agg = bucket.getAggregations().get("expert_agg");
            dto.setExpert((String) expert_agg.getBuckets().get(0).getKey());
            final ParsedStringTerms deptName_agg = bucket.getAggregations().get("deptName_agg");
            dto.setDeptName((String) deptName_agg.getBuckets().get(0).getKey());
            final ParsedStringTerms hospitalName = bucket.getAggregations().get("hospitalName_agg");
            dto.setHospitalName((String) hospitalName.getBuckets().get(0).getKey());
            final ParsedStringTerms illNameList_agg = bucket.getAggregations().get("illNameList_agg");
            dto.setIllNameList(List.of(((String) illNameList_agg.getBuckets().get(0).getKey()).split(",")));
            final ParsedStringTerms levelName_agg = bucket.getAggregations().get("levelName_agg");
            dto.setLevelName((String) levelName_agg.getBuckets().get(0).getKey());
            final ParsedStringTerms deptCode_agg = bucket.getAggregations().get("deptCode_agg");
            dto.setDeptCode((String) deptCode_agg.getBuckets().get(0).getKey());
            final ParsedStringTerms hospitalCode_agg = bucket.getAggregations().get("hospitalCode_agg");
            dto.setHospitalCode((String) hospitalCode_agg.getBuckets().get(0).getKey());
            final ParsedMax status_agg = bucket.getAggregations().get("status_agg");
            dto.setStatus((int) status_agg.getValue());
            return dto;
        }).collect(Collectors.toList());

        return collect;
    }
}
