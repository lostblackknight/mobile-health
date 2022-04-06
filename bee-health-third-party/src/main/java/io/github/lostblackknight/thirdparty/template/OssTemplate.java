package io.github.lostblackknight.thirdparty.template;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import io.github.lostblackknight.thirdparty.properties.AliYunProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OssTemplate implements InitializingBean {

    private final AliYunProperties aliYunProperties;

    private final OSSClient ossClient;

    private String bucketName;

    @Component
    @RequiredArgsConstructor
    public static class Provider {
        private final AliYunProperties aliYunProperties;

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public OSSClient getOssClient() {
            final AliYunProperties.Oss oss = aliYunProperties.getOss();
            final OSSClient ossClient = (OSSClient) new OSSClientBuilder().build(oss.getEndpoint(), oss.getAccessKeyId(), oss.getAccessKeySecret());
            log.info("创建：{}", ossClient);
            return ossClient;
        }
    }


    public void uploadFile(String fileName, InputStream inputStream) throws OSSException, ClientException {
        this.ossClient.putObject(this.bucketName, fileName, inputStream);
    }

    @Override
    public void afterPropertiesSet() {
        final AliYunProperties.Oss oss = aliYunProperties.getOss();
        this.bucketName = oss.getBucketName();
    }
}
