package ink.baojie.cloud.provider8201impl.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "settings")
public class Settings {
    private String msg;
}
