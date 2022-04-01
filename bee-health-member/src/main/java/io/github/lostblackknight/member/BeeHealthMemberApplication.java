package io.github.lostblackknight.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BeeHealthMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthMemberApplication.class, args);
    }

}
