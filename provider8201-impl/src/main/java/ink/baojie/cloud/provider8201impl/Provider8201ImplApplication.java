package ink.baojie.cloud.provider8201impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Provider8201ImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(Provider8201ImplApplication.class, args);
    }

}
