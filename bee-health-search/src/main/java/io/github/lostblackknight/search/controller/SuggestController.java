package io.github.lostblackknight.search.controller;

import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.search.service.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class SuggestController {

    private final SuggestService suggestService;

    @GetMapping("/suggest")
    public CommonResult<?> suggest(@RequestParam String keyword) {
        final List<String> list = suggestService.getSuggestByKeyword(keyword);
        return CommonResult.success(Map.of("suggest", list));
    }
}
