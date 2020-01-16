package ink.baojie.cloud.tx8205impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("ink.baojie.cloud.tx8205impl.dao")
public class Tx8205ImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tx8205ImplApplication.class, args);
    }

}
