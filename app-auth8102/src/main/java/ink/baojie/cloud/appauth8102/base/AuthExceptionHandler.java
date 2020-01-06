package ink.baojie.cloud.appauth8102.base;

import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.exception.BaseError;
import ink.baojie.cloud.base.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author renbaojie
 */
@Slf4j
@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseOutDTO defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.error("接口: {} 调用失败", req.getRequestURI());
        log.error("异常信息: ", e);
        return new BaseOutDTO(null).fail(BaseError.SYS_ERR);
    }

    @ExceptionHandler(value = BaseRuntimeException.class)
    @ResponseBody
    public BaseOutDTO knownErrorHandler(HttpServletRequest req, BaseRuntimeException e) {
        log.error("接口: {} 调用失败 : {}", req.getRequestURI(), e.getMessage());
        return new BaseOutDTO(null).fail(new BaseError().setCode(e.getCode()).setMsg(e.getMessage()));
    }
}
