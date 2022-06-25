package io.github.lostblackknight.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("io.github.lostblackknight.*")
@RefreshScope
public class BeeHealthTopicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthTopicApplication.class, args);
    }

}
