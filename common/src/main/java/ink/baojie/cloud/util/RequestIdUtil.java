package ink.baojie.cloud.util;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 生成全局唯一请求id
 *
 * @author renbaojie
 */
public class RequestIdUtil {
    public static String createId() {
        return (LocalDate.now().toString() + UUID.randomUUID().toString()).replace("-","");
    }
}
