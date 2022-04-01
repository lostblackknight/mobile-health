package io.github.lostblackknight.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BeeHealthSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthSearchApplication.class, args);
    }

}
