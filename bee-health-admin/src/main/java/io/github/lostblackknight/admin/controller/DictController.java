package io.github.lostblackknight.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import io.github.lostblackknight.model.dto.DictOptionsDTO;
import io.github.lostblackknight.admin.service.DictService;
import io.github.lostblackknight.admin.support.TokenInfoContextHolder;
import io.github.lostblackknight.admin.vo.DictAddForm;
import io.github.lostblackknight.admin.vo.DictEditForm;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.admin.Dict;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DictController {

    private final DictService dictService;


    @GetMapping("/dict/{id}")
    public CommonResult<?> getDictById(@PathVariable Long id) {
        final Dict dict = dictService.getById(id);
        return CommonResult.success(dict);
    }

    @GetMapping("/dict/options")
    public CommonResult<?> getDictOptions() {
        final List<DictOptionsDTO> dictOptions = dictService.getDictOptions();
        return CommonResult.success(dictOptions);
    }

    @GetMapping("/dict")
    public CommonResult<?> getDict(@RequestParam(required = false) String dictLabel) {
        List<Dict> dictList = dictService.getDict(dictLabel);
        return CommonResult.success(dictList);
    }

    @GetMapping("/dict/dictValue/{dictValue}")
    public CommonResult<Dict> getDictByDictValue(@PathVariable String dictValue) {
        Dict dict = dictService.getDictByDictValue(dictValue);
        return CommonResult.success(dict);
    }

    @PostMapping("/dict")
    public CommonResult<?> createDict(@RequestBody DictAddForm dictAddForm) {
        final Dict dict = new Dict();
        dict.setParentId(dictAddForm.getParentId());
        dict.setDictLabel(dictAddForm.getDictLabel());
        dict.setDictValue(dictAddForm.getDictValue());
        dict.setDictSort(dictAddForm.getDictSort());
        final TokenInfoDTO tokenInfoDTO = TokenInfoContextHolder.get();
        dict.setCreateBy(tokenInfoDTO.getPrincipal());
        return dictService.save(dict) ? CommonResult.success("添加成功") : CommonResult.fail("添加失败");
    }

    @PutMapping("/dict")
    public CommonResult<?> modifyDict(@RequestBody DictEditForm dictEditForm) {
        final Dict dict = new Dict();
        BeanUtil.copyProperties(dictEditForm, dict);
        return dictService.updateById(dict) ? CommonResult.success("修改成功") : CommonResult.success("修改失败");
    }

    @DeleteMapping("/dict/{id}")
    public CommonResult<?> removeDictById(@PathVariable Long id) {
        boolean flag = dictService.removeDictById(id);
        return flag ? CommonResult.success("删除成功") : CommonResult.fail("删除失败，存在关联项");
    }

    @DeleteMapping("/dict/batch")
    public CommonResult<?> removeBatchDictByIds(@RequestBody List<Long> ids) {
        boolean flag = dictService.removeBatchDictByIds(ids);
        return flag ? CommonResult.success("删除成功") : CommonResult.fail("删除失败，存在关联项");
    }
}
