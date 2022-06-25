package io.github.lostblackknight.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("io.github.lostblackknight.*")
@RefreshScope
public class BeeHealthSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthSearchApplication.class, args);
    }

}
