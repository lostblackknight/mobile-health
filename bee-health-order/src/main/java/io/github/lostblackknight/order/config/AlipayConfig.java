package io.github.lostblackknight.order.config;

import io.github.lostblackknight.order.support.AlipayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayConfig {

}