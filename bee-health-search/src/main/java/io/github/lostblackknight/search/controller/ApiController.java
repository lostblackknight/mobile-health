package io.github.lostblackknight.search.controller;

import cn.hutool.core.bean.BeanUtil;
import io.github.lostblackknight.model.entity.search.*;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.search.entity.*;
import io.github.lostblackknight.search.repository.ArticleRepository;
import io.github.lostblackknight.search.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.completion.Completion;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final HospitalService hospitalService;

    private final DeptService deptService;

    private final ScheduleService scheduleService;

    private final SuggestService suggestService;

    private final ArticleService articleService;

    @PostMapping("/hospitals/upload")
    public CommonResult<?> uploadHospitalToES(@RequestBody HospitalES hospitalES) {
        final HospitalESModel model = new HospitalESModel();
        BeanUtil.copyProperties(hospitalES, model);
        hospitalService.saveHospital(model);
        return CommonResult.success("上传成功");
    }

    @PostMapping("/depts/upload")
    public CommonResult<?> uploadDeptToES(@RequestBody DeptES deptES) {
        final DeptESModel model = new DeptESModel();
        BeanUtil.copyProperties(deptES, model);
        deptService.saveDept(model);
        return CommonResult.success("上传成功");
    }

    @PostMapping("/schedules/upload")
    public CommonResult<?> uploadSchedulesToES(@RequestBody ScheduleES scheduleES) {
        final ScheduleESModel model = new ScheduleESModel();
        BeanUtil.copyProperties(scheduleES, model);
        scheduleService.saveSchedule(model);
        return CommonResult.success("上传成功");
    }

    @PostMapping("/suggests/upload")
    public CommonResult<?> uploadSuggestToES(@RequestBody SuggestES suggestES) {
        final SuggestESModel model = new SuggestESModel();
        model.setId(suggestES.getId());
        model.setSuggest(new Completion(List.of(suggestES.getSuggest())));
        suggestService.saveKeyword(model);
        return CommonResult.success("上传成功");
    }

    @PostMapping("/articles/upload")
    public CommonResult<?> uploadArticleToES(@RequestBody ArticleES articleES) {
        final ArticleESModel model = new ArticleESModel();
        BeanUtil.copyProperties(articleES, model);
        articleService.saveArticle(model);
        return CommonResult.success("上传成功");
    }
}
