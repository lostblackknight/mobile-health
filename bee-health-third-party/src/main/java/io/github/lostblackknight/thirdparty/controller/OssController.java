package io.github.lostblackknight.thirdparty.controller;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.thirdparty.template.OssTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class OssController {

    private final OssTemplate ossTemplate;

    @PostMapping("/oss/upload/image")
    public CommonResult<?> uploadImage(MultipartFile file) {
        final String fileName = "bee-health/" + IdUtil.fastSimpleUUID() + "." + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        try {
            ossTemplate.uploadFile(fileName, file.getInputStream());
        } catch (OSSException | ClientException | IOException e) {
            log.error("图片上传失败：{}", e.getMessage());
            return CommonResult.fail("图片上传失败");
        }
        String url = "https://bee-health.oss-cn-beijing.aliyuncs.com/" + fileName;
        return CommonResult.success(Map.of("url", url), "图片上传成功");
    }
}
