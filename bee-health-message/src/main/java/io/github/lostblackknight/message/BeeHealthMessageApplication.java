package io.github.lostblackknight.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BeeHealthMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthMessageApplication.class, args);
    }

}
