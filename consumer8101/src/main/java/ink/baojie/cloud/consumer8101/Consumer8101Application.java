package ink.baojie.cloud.consumer8101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Consumer8101Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer8101Application.class, args);
    }

}
