package io.github.lostblackknight.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jwt 相关配置
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * 访问 token
     */
    private AccessToken accessToken;
    /**
     * 刷新 token
     */
    private RefreshToken refreshToken;

    /**
     * 访问 token
     */
    @Data
    public static class AccessToken {
        /**
         * 秘钥
         */
        private String secret;
        /**
         * 过期时间 单位为分钟
         */
        private long expiresAt;
    }

    /**
     * 刷新 token
     */
    @Data
    public static class RefreshToken {
        /**
         * 秘钥
         */
        private String secret;
        /**
         * 过期时间 单位为分钟
         */
        private long expiresAt;
    }
}
