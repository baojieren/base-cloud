package ink.baojie.cloud.provider8201impl.base;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
public class LogAspect {

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
        ThreadLocal<Map> threadLocal = new ThreadLocal<>();
        ConcurrentHashMap<String, Object> parameterMap = new ConcurrentHashMap<>(3);
        long startTime = System.currentTimeMillis();
        parameterMap.put("startTime", startTime);

        String methodClassName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        parameterMap.put("method", methodClassName + "." + methodName);

        Object[] parameterValues = joinPoint.getArgs();
        Parameter[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();

        StringBuilder sb = new StringBuilder();
        if (!ArrayUtils.isEmpty(parameterValues) && !ArrayUtils.isEmpty(parameterNames)) {
            for (int i = 0; i < parameterValues.length; i++) {
                String parameterName = parameterNames[i].getName();
                Object parameterValue = parameterValues[i];
                sb.append(parameterName).append("=");
                if ((parameterValue instanceof String) || (parameterValue instanceof Integer)) {
                    sb.append(parameterValue).append(",");
                } else {
                    sb.append(JSON.toJSONString(parameterValue)).append(",");
                }
            }
        }

        parameterMap.put("arg", sb.toString());
        threadLocal.set(parameterMap);
        log.info("请求--->: {},\t参数: {}", parameterMap.get("method"), parameterMap.get("arg"));

        Object result = null;
        try {
            // 执行实际方法，result为方法执行返回值
            result = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map getMap = threadLocal.get();
        log.info("响应<---: {},\t时间: {}ms,\t结果: {}",
                getMap.get("method"),
                System.currentTimeMillis() - (long) getMap.get("startTime"),
                result == null ? null : JSON.toJSONString(result));

        threadLocal.remove();
        return result;
    }
}
