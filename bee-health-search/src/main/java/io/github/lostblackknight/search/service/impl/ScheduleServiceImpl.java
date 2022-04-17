package io.github.lostblackknight.search.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.lostblackknight.model.dto.DoctorDTO;
import io.github.lostblackknight.model.dto.ScheduleDateDTO;
import io.github.lostblackknight.model.vo.ScheduleDateParam;
import io.github.lostblackknight.model.vo.ScheduleDeptParam;
import io.github.lostblackknight.model.vo.ScheduleDoctorParam;
import io.github.lostblackknight.model.vo.ScheduleParam;
import io.github.lostblackknight.search.entity.DeptESModel;
import io.github.lostblackknight.search.entity.ScheduleESModel;
import io.github.lostblackknight.search.repository.ScheduleRepository;
import io.github.lostblackknight.search.service.ScheduleService;
import io.github.lostblackknight.search.vo.DeptDoctorDTO;
import io.github.lostblackknight.search.vo.DoctorScheduleDTO;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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

        if (StrUtil.isNotEmpty(param.getDoctorCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("doctorCode", param.getDoctorCode()));
        }

        if (ObjectUtil.isNotEmpty(param.getMemberId())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("memberId", param.getMemberId()));
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
        doctorNameAgg.subAggregation(AggregationBuilders.terms("doctorCode_agg").field("doctorCode.keyword"));
        doctorNameAgg.subAggregation(AggregationBuilders.terms("memberId_agg").field("memberId"));
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
            final ParsedStringTerms doctorCode_agg = bucket.getAggregations().get("doctorCode_agg");
            dto.setDoctorCode((String) doctorCode_agg.getBuckets().get(0).getKey());
            final ParsedLongTerms memberId_agg = bucket.getAggregations().get("memberId_agg");
            dto.setMemberId((Long) memberId_agg.getBuckets().get(0).getKey());
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

    @Override
    public List<DeptDoctorDTO> getDeptListByDoctorCode(ScheduleDeptParam param) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (StrUtil.isNotEmpty(param.getHospitalCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hospitalCode", param.getHospitalCode()));
        }

        if (StrUtil.isNotEmpty(param.getDoctorCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("doctorCode", param.getDoctorCode()));
        }

        final TermsAggregationBuilder deptCodeAgg = AggregationBuilders.terms("deptCode_agg").field("deptCode.keyword").size(100);

        deptCodeAgg.subAggregation(AggregationBuilders.terms("deptName_agg").field("deptName.keyword").size(100));

        final NativeSearchQuery query = new NativeSearchQuery(boolQueryBuilder);

        query.addAggregation(deptCodeAgg);


        query.setPageable(PageRequest.of(param.getPageNum() - 1, param.getPageSize()));

        final SearchHits<ScheduleESModel> searchHits = elasticsearchRestTemplate.search(query, ScheduleESModel.class);

        final Aggregations aggregations = searchHits.getAggregations();

        final ParsedStringTerms deptCode_agg = aggregations.get("deptCode_agg");

        final List<DeptDoctorDTO> list = deptCode_agg.getBuckets().stream().map(bucket -> {
            final DeptDoctorDTO dto = new DeptDoctorDTO();
            dto.setDeptCode((String) bucket.getKey());
            final ParsedStringTerms deptName_agg = bucket.getAggregations().get("deptName_agg");
            dto.setDeptName((String) deptName_agg.getBuckets().get(0).getKey());
            return dto;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public DoctorScheduleDTO getDoctorSchedule(ScheduleParam param) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (StrUtil.isNotEmpty(param.getHospitalCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hospitalCode", param.getHospitalCode()));
        }

        if (StrUtil.isNotEmpty(param.getDoctorCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("doctorCode", param.getDoctorCode()));
        }

        if (StrUtil.isNotEmpty(param.getDeptCode())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("deptCode", param.getDeptCode()));
        }

        final TermsAggregationBuilder dateAgg = AggregationBuilders.terms("date_agg").field("date").size(100).order(BucketOrder.key(true));
        final TermsAggregationBuilder weekAgg = AggregationBuilders.terms("week_agg").field("week.keyword").size(100);

        final TermsAggregationBuilder timeTypeAgg = AggregationBuilders.terms("timeType_agg").field("timeType.keyword").size(100);

        dateAgg.subAggregation(weekAgg);
        dateAgg.subAggregation(timeTypeAgg);

        timeTypeAgg.subAggregation(AggregationBuilders.terms("amount_agg").field("amount").size(100));
        timeTypeAgg.subAggregation(AggregationBuilders.terms("yuYueState_agg").field("yuYueState").size(100));
        timeTypeAgg.subAggregation(AggregationBuilders.terms("yuYueMax_agg").field("yuYueMax").size(100));
        timeTypeAgg.subAggregation(AggregationBuilders.terms("yuYueNum_agg").field("yuYueNum").size(100));
        timeTypeAgg.subAggregation(AggregationBuilders.terms("scheduleId_agg").field("scheduleId.keyword").size(100));

        final NativeSearchQuery query = new NativeSearchQuery(boolQueryBuilder);

        query.addAggregation(dateAgg);

        query.setPageable(PageRequest.of(param.getPageNum() - 1, param.getPageSize()));

        final SearchHits<ScheduleESModel> searchHits = elasticsearchRestTemplate.search(query, ScheduleESModel.class);


        final ScheduleESModel model = searchHits.getSearchHits().get(0).getContent();

        final Aggregations aggregations = searchHits.getAggregations();

        final ParsedLongTerms date_agg = aggregations.get("date_agg");

        final DoctorScheduleDTO dto = new DoctorScheduleDTO();
        dto.setHospitalCode(model.getHospitalCode());
        dto.setHospitalName(model.getHospitalName());
        dto.setDeptCode(model.getDeptCode());
        dto.setDeptName(model.getDeptName());
        dto.setDoctorCode(model.getDoctorCode());
        dto.setDoctorName(model.getDoctorName());
        dto.setMemberId(model.getMemberId());
        dto.setExpert(model.getExpert());
        dto.setIllNameList(List.of(model.getIllNameList().split(",")));
        dto.setIcon(model.getIcon());
        dto.setLevelName(model.getLevelName());
        final ArrayList<DoctorScheduleDTO.Schedule> schedules = new ArrayList<>();

        date_agg.getBuckets().forEach(bucket -> {
            final Date date = new Date((Long) bucket.getKey());
            final DoctorScheduleDTO.Schedule schedule = new DoctorScheduleDTO.Schedule();
            schedule.setDate(date);
            schedule.setDay(String.valueOf(date.getDate() < 10 ? "0" + date.getDate() : date.getDate()));

            final ParsedStringTerms week_agg = bucket.getAggregations().get("week_agg");

            schedule.setWeek((String) week_agg.getBuckets().get(0).getKey());

            final ParsedStringTerms timeType_agg = bucket.getAggregations().get("timeType_agg");

            if (timeType_agg.getBuckets().size() == 0) {
                schedule.setAm(null);
                schedule.setPm(null);
            }

            if (timeType_agg.getBuckets().size() == 1) {
                if (timeType_agg.getBuckets().get(0).getKey().equals("am")) {
                    final DoctorScheduleDTO.ScheduleDetail am = new DoctorScheduleDTO.ScheduleDetail();
                    final ParsedStringTerms scheduleId_agg = timeType_agg.getBuckets().get(0).getAggregations().get("scheduleId_agg");
                    am.setScheduleId((String) scheduleId_agg.getBuckets().get(0).getKey());
                    am.setTimeType((String) timeType_agg.getBuckets().get(0).getKey());
                    final ParsedDoubleTerms amount_agg = timeType_agg.getBuckets().get(0).getAggregations().get("amount_agg");
                    am.setAmount(BigDecimal.valueOf((Double) amount_agg.getBuckets().get(0).getKey()));
                    final ParsedLongTerms yuYueState_agg = timeType_agg.getBuckets().get(0).getAggregations().get("yuYueState_agg");
                    am.setYuYueState(Math.toIntExact((Long) yuYueState_agg.getBuckets().get(0).getKey()));
                    final ParsedLongTerms yuYueMax_agg = timeType_agg.getBuckets().get(0).getAggregations().get("yuYueMax_agg");
                    am.setYuYueMax(Math.toIntExact((Long) yuYueMax_agg.getBuckets().get(0).getKey()));
                    final ParsedLongTerms yuYueNum_agg = timeType_agg.getBuckets().get(0).getAggregations().get("yuYueNum_agg");
                    am.setYuYueNum(Math.toIntExact((Long) yuYueNum_agg.getBuckets().get(0).getKey()));
                    schedule.setAm(am);
                }
                if (timeType_agg.getBuckets().get(0).getKey().equals("pm")) {
                    final DoctorScheduleDTO.ScheduleDetail pm = new DoctorScheduleDTO.ScheduleDetail();
                    final ParsedStringTerms scheduleId_agg1 = timeType_agg.getBuckets().get(0).getAggregations().get("scheduleId_agg");
                    pm.setScheduleId((String) scheduleId_agg1.getBuckets().get(0).getKey());
                    pm.setTimeType((String) timeType_agg.getBuckets().get(0).getKey());
                    final ParsedDoubleTerms amount_agg1 = timeType_agg.getBuckets().get(0).getAggregations().get("amount_agg");
                    pm.setAmount(BigDecimal.valueOf((Double) amount_agg1.getBuckets().get(0).getKey()));
                    final ParsedLongTerms yuYueState_agg1 = timeType_agg.getBuckets().get(0).getAggregations().get("yuYueState_agg");
                    pm.setYuYueState(Math.toIntExact((Long) yuYueState_agg1.getBuckets().get(0).getKey()));
                    final ParsedLongTerms yuYueMax_agg1 = timeType_agg.getBuckets().get(0).getAggregations().get("yuYueMax_agg");
                    pm.setYuYueMax(Math.toIntExact((Long) yuYueMax_agg1.getBuckets().get(0).getKey()));
                    final ParsedLongTerms yuYueNum_agg1 = timeType_agg.getBuckets().get(0).getAggregations().get("yuYueNum_agg");
                    pm.setYuYueNum(Math.toIntExact((Long) yuYueNum_agg1.getBuckets().get(0).getKey()));

                    schedule.setPm(pm);
                }
            }

            if (timeType_agg.getBuckets().size() == 2) {
                final DoctorScheduleDTO.ScheduleDetail am = new DoctorScheduleDTO.ScheduleDetail();
                final ParsedStringTerms scheduleId_agg = timeType_agg.getBuckets().get(0).getAggregations().get("scheduleId_agg");
                am.setScheduleId((String) scheduleId_agg.getBuckets().get(0).getKey());
                am.setTimeType((String) timeType_agg.getBuckets().get(0).getKey());
                final ParsedDoubleTerms amount_agg = timeType_agg.getBuckets().get(0).getAggregations().get("amount_agg");
                am.setAmount(BigDecimal.valueOf((Double) amount_agg.getBuckets().get(0).getKey()));
                final ParsedLongTerms yuYueState_agg = timeType_agg.getBuckets().get(0).getAggregations().get("yuYueState_agg");
                am.setYuYueState(Math.toIntExact((Long) yuYueState_agg.getBuckets().get(0).getKey()));
                final ParsedLongTerms yuYueMax_agg = timeType_agg.getBuckets().get(0).getAggregations().get("yuYueMax_agg");
                am.setYuYueMax(Math.toIntExact((Long) yuYueMax_agg.getBuckets().get(0).getKey()));
                final ParsedLongTerms yuYueNum_agg = timeType_agg.getBuckets().get(0).getAggregations().get("yuYueNum_agg");
                am.setYuYueNum(Math.toIntExact((Long) yuYueNum_agg.getBuckets().get(0).getKey()));


                schedule.setAm(am);

                final DoctorScheduleDTO.ScheduleDetail pm = new DoctorScheduleDTO.ScheduleDetail();
                final ParsedStringTerms scheduleId_agg1 = timeType_agg.getBuckets().get(1).getAggregations().get("scheduleId_agg");
                pm.setScheduleId((String) scheduleId_agg1.getBuckets().get(0).getKey());
                pm.setTimeType((String) timeType_agg.getBuckets().get(1).getKey());
                final ParsedDoubleTerms amount_agg1 = timeType_agg.getBuckets().get(1).getAggregations().get("amount_agg");
                pm.setAmount(BigDecimal.valueOf((Double) amount_agg1.getBuckets().get(0).getKey()));
                final ParsedLongTerms yuYueState_agg1 = timeType_agg.getBuckets().get(1).getAggregations().get("yuYueState_agg");
                pm.setYuYueState(Math.toIntExact((Long) yuYueState_agg1.getBuckets().get(0).getKey()));
                final ParsedLongTerms yuYueMax_agg1 = timeType_agg.getBuckets().get(1).getAggregations().get("yuYueMax_agg");
                pm.setYuYueMax(Math.toIntExact((Long) yuYueMax_agg1.getBuckets().get(0).getKey()));
                final ParsedLongTerms yuYueNum_agg1 = timeType_agg.getBuckets().get(1).getAggregations().get("yuYueNum_agg");
                pm.setYuYueNum(Math.toIntExact((Long) yuYueNum_agg1.getBuckets().get(0).getKey()));

                schedule.setPm(pm);
            }

            schedules.add(schedule);
        });

        dto.setSchedules(schedules);

        return dto;
    }
}
