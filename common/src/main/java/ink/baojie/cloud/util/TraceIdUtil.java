package ink.baojie.cloud.util;

/**
 * 调用链id工具类
 *
 * @author renbaojie
 */
public class TraceIdUtil {

    public static String TRACE_ID = "traceId";

    private static final ThreadLocal<String> THREAD_LOCAL_TRACE_ID = new ThreadLocal<>();

    /**
     * 生成traceId
     */
    public static String createTraceId() {
        String traceId = System.currentTimeMillis() + RandomUtil.genStr(5);
        setTraceId(traceId);
        return traceId;
    }

    public static String getTraceId() {
        return THREAD_LOCAL_TRACE_ID.get();
    }

    public static void setTraceId(String traceId) {
        THREAD_LOCAL_TRACE_ID.set(traceId);
    }

    public static void removeTraceId() {
        THREAD_LOCAL_TRACE_ID.remove();
    }

}
