package io.github.lostblackknight.message;

import io.github.lostblackknight.message.support.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("io.github.lostblackknight.*")
public class BeeHealthMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthMessageApplication.class, args);
    }

    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

}
