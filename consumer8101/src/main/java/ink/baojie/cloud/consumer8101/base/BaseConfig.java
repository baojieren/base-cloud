package ink.baojie.cloud.consumer8101.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "base-config")
public class BaseConfig {
    private String msg;
}
