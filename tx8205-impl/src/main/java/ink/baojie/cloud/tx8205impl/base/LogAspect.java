package ink.baojie.cloud.tx8205impl.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ink.baojie.cloud.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogAspect {

    // private static ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    @Pointcut("@within(org.apache.dubbo.config.annotation.Service)")
    public void dubboLog() {
    }

    @Before("dubboLog()")
    public void theBefore(JoinPoint joinPoint) {
    }

    @After("dubboLog()")
    public void theAfter() {
    }

    @Around("dubboLog()")
    public Object theAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Map<String, Object> currMap = new HashMap<>(3);
        THREAD_LOCAL.set(currMap);

        String methodClassName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String requestMethod = methodClassName + "." + methodName;

        Object[] parameterValues = joinPoint.getArgs();
        Parameter[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();

        StringBuilder sb = new StringBuilder();
        if (!ArrayUtils.isEmpty(parameterValues) && !ArrayUtils.isEmpty(parameterNames)) {
            for (int i = 0; i < parameterValues.length; i++) {
                String parameterName = parameterNames[i].getName();
                Object parameterValue = parameterValues[i];
                sb.append(parameterName).append("= ");
                if ((parameterValue instanceof String) || (parameterValue instanceof Integer)) {
                    sb.append(parameterValue);
                } else {
                    sb.append(JSON.toJSONString(parameterValue));
                }
                if (i != parameterValues.length - 1) {
                    sb.append(",");
                }
            }
        }

        currMap.put("traceId", RpcContext.getContext().getAttachment(TraceIdUtil.TRACE_ID));
        currMap.put("startTime", startTime);
        currMap.put("method", requestMethod);
        log.info("请求--->: {},\ttraceId: {},\t参数: {}", requestMethod, currMap.get("traceId"), sb.toString());

        // 执行实际方法，result为方法执行返回值, 提供者不要try, 会导致消费者获取不到异常信息
        Object result = joinPoint.proceed();

        Map getMap = THREAD_LOCAL.get();
        log.info("响应<---: {},\ttraceId: {},\t耗时: {}ms,\t结果: {}",
                getMap.get("method"),
                getMap.get("traceId"),
                System.currentTimeMillis() - (long) getMap.get("startTime"),
                result == null ? null : JSON.toJSONString(result));

        THREAD_LOCAL.remove();
        TraceIdUtil.removeTraceId();
        return result;
    }
}
