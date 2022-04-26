package io.github.lostblackknight.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("io.github.lostblackknight.*")
public class BeeHealthThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthThirdPartyApplication.class, args);
    }

}
