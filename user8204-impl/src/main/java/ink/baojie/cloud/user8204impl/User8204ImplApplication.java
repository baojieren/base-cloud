package ink.baojie.cloud.user8204impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("ink.baojie.cloud.user8204impl.dao")
public class User8204ImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(User8204ImplApplication.class, args);
    }

}
