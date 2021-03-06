package io.github.lostblackknight.hospital.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lostblackknight.hospital.service.HospitalClientDetailService;
import io.github.lostblackknight.hospital.vo.HospitalClientDetailAddForm;
import io.github.lostblackknight.hospital.vo.HospitalClientDetailEditForm;
import io.github.lostblackknight.hospital.vo.HospitalClientDetailPageQueryVO;
import io.github.lostblackknight.hospital.vo.HospitalClientIdSecretVO;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.entity.hospital.HospitalClientDetail;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.util.PageUtils;
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
public class HospitalClientDetailController {

    private final HospitalClientDetailService hospitalClientDetailService;

    @GetMapping("/hospitalClientDetails/{id}")
    public CommonResult<?> getHospitalClientDetailById(@PathVariable Long id) {
        final HospitalClientDetail hospitalClientDetail = hospitalClientDetailService.getById(id);
        return CommonResult.success(hospitalClientDetail);
    }

    @GetMapping("/hospitalClientDetails/list")
    public CommonResult<?> getHospitalClientDetailByList() {
        return CommonResult.success(hospitalClientDetailService.list());
    }

    @PutMapping("/hospitalClientDetails/{id}/status/{status}")
    public CommonResult<?> modifyHospitalClientDetailStatusById(@PathVariable Long id, @PathVariable Integer status) {
        boolean flag = hospitalClientDetailService.modifyHospitalClientDetailStatusById(id, status);
        return flag ? CommonResult.success("??????????????????") : CommonResult.fail("??????????????????");
    }

    @GetMapping("/hospitalClientDetails/page/{pageNum}/{pageSize}")
    public CommonResult<?> getHospitalClientDetailByPage(@PathVariable Long pageNum,
                                                         @PathVariable Long pageSize,
                                                         HospitalClientDetailPageQueryVO hospitalClientDetailPageQueryVO) {
        log.info("???????????????: {}", hospitalClientDetailPageQueryVO);
        final QueryWrapper<HospitalClientDetail> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(hospitalClientDetailPageQueryVO)) {
            if (StrUtil.isNotEmpty(hospitalClientDetailPageQueryVO.getHospitalName())) {
                wrapper.like("hospital_name", hospitalClientDetailPageQueryVO.getHospitalName());
            }
            if (StrUtil.isNotEmpty(hospitalClientDetailPageQueryVO.getContactsName())) {
                wrapper.like("contacts_name", hospitalClientDetailPageQueryVO.getContactsName());
            }
            if (StrUtil.isNotEmpty(hospitalClientDetailPageQueryVO.getContactsPhone())) {
                wrapper.eq("contacts_phone", hospitalClientDetailPageQueryVO.getContactsPhone());
            }
        }
        wrapper.orderByDesc("update_time");

        final Page<HospitalClientDetail> page = hospitalClientDetailService.page(Page.of(pageNum, pageSize), wrapper);

        final PageDTO<HospitalClientDetail> pageDTO = PageUtils.toPage(page);

        return CommonResult.success(pageDTO);
    }

    @PostMapping("/hospitalClientDetails")
    public CommonResult<?> createHospitalClientDetail(@RequestBody HospitalClientDetailAddForm hospitalClientDetailAddForm) {
        final HospitalClientDetail hospitalClientDetail = new HospitalClientDetail();
        final String id = IdUtil.fastSimpleUUID();
        hospitalClientDetail.setHospitalId(id);
        final String secret = IdUtil.fastUUID();
        hospitalClientDetail.setHospitalSecret(secret);
        BeanUtil.copyProperties(hospitalClientDetailAddForm, hospitalClientDetail);
        final boolean flag = hospitalClientDetailService.createHospitalClientDetail(hospitalClientDetail);
        final HospitalClientIdSecretVO hospitalClientIdSecretVO = new HospitalClientIdSecretVO();
        hospitalClientIdSecretVO.setClientId(hospitalClientDetail.getHospitalId());
        hospitalClientIdSecretVO.setClientSecret(hospitalClientDetail.getHospitalSecret());
        return flag ? CommonResult.success(hospitalClientIdSecretVO, "????????????") : CommonResult.fail("????????????");
    }

    @PutMapping("/hospitalClientDetails")
    public CommonResult<?> modifyHospitalClientDetail(@RequestBody HospitalClientDetailEditForm hospitalClientDetailEditForm) {
        final HospitalClientDetail hospitalClientDetail = new HospitalClientDetail();
        BeanUtil.copyProperties(hospitalClientDetailEditForm, hospitalClientDetail);
        final boolean flag = hospitalClientDetailService.updateById(hospitalClientDetail);
        return flag ? CommonResult.success("????????????") : CommonResult.fail("????????????");
    }

    @DeleteMapping("/hospitalClientDetails/{id}")
    public CommonResult<?> removeHospitalClientDetail(@PathVariable Long id) {
        final boolean flag = hospitalClientDetailService.removeHospitalClientDetailById(id);
        return flag ? CommonResult.success("????????????") : CommonResult.fail("??????????????????????????????");
    }

    @DeleteMapping("/hospitalClientDetails/batch")
    public CommonResult<?> removeBatchHospitalClientDetail(@RequestBody List<Long> ids) {
        final boolean flag = hospitalClientDetailService.removeBatchHospitalClientDetail(ids);
        return flag ? CommonResult.success("????????????") : CommonResult.fail("??????????????????????????????");
    }
}
