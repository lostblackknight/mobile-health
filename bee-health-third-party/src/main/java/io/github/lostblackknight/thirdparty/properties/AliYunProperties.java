package io.github.lostblackknight.thirdparty.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@ConfigurationProperties("aliyun")
public class AliYunProperties {

    private Oss oss;
    private Sms sms;

    @Data
    public static class Oss {
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
        private String bucketName;
    }

    @Data
    public static class Sms {
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
    }
}
