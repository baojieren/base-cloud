package ink.baojie.cloud.appauth8102.base;

import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.exception.BaseError;
import ink.baojie.cloud.base.exception.BaseRuntimeException;
import ink.baojie.cloud.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author renbaojie
 */
@Slf4j
@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseOutDTO defaultErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e) {
        log.error("接口: {} 调用失败", req.getRequestURI());
        log.error("异常信息: ", e);
        response.addHeader(TraceIdUtil.TRACE_ID, TraceIdUtil.getTraceId());
        return new BaseOutDTO().fail(BaseError.SYS_ERR);
    }

    @ExceptionHandler(value = BaseRuntimeException.class)
    @ResponseBody
    public BaseOutDTO knownErrorHandler(HttpServletRequest req, HttpServletResponse response, BaseRuntimeException e) {
        log.error("接口: {} 调用失败 : {}", req.getRequestURI(), e.getMessage());
        response.addHeader(TraceIdUtil.TRACE_ID, TraceIdUtil.getTraceId());
        return new BaseOutDTO().fail(new BaseError().setCode(e.getCode()).setMsg(e.getMessage()));
    }
}
