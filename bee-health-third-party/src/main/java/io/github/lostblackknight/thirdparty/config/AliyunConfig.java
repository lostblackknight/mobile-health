package io.github.lostblackknight.thirdparty.config;

import io.github.lostblackknight.thirdparty.properties.AliYunProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Configuration
@EnableConfigurationProperties(AliYunProperties.class)
public class AliyunConfig {
}
