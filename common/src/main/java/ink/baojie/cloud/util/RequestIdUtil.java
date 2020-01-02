package ink.baojie.cloud.util;

/**
 * 生成全局唯一请求id
 *
 * @author renbaojie
 */
public class RequestIdUtil {
    public static String genRequestId() {
        // return LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + RandomUtil.genStr(10);
        return RandomUtil.genStr(10);
    }
}
