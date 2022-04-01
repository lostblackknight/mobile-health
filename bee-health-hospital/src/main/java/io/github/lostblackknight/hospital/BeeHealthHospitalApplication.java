package io.github.lostblackknight.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BeeHealthHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeHealthHospitalApplication.class, args);
    }

}
