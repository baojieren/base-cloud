package ink.baojie.cloud.appauth8102;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppAuth8102Application {

    public static void main(String[] args) {
        SpringApplication.run(AppAuth8102Application.class, args);
    }

}
