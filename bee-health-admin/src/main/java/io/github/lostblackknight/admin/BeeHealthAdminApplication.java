package io.github.lostblackknight.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("io.github.lostblackknight.*")
public class BeeHealthAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthAdminApplication.class, args);
    }

}
