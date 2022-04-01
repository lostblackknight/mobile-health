package io.github.lostblackknight.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BeeHealthTopicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthTopicApplication.class, args);
    }

}
