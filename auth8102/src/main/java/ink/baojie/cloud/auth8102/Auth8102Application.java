package ink.baojie.cloud.auth8102;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Auth8102Application {

    public static void main(String[] args) {
        SpringApplication.run(Auth8102Application.class, args);
    }

}
