package io.github.lostblackknight.member;

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
public class BeeHealthMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthMemberApplication.class, args);
    }

}
