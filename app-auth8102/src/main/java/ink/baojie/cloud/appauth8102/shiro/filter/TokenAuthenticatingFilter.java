package ink.baojie.cloud.appauth8102.shiro.filter;

import com.alibaba.fastjson.JSON;
import ink.baojie.cloud.appauth8102.base.AuthError;
import ink.baojie.cloud.appauth8102.shiro.token.JwtToken;
import ink.baojie.cloud.appauth8102.util.JwtUtils;
import ink.baojie.cloud.appauth8102.util.ResponseUtil;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户使用token登录的过滤器
 *
 * @author renbaojie
 */
@Slf4j
public class TokenAuthenticatingFilter extends AuthenticatingFilter {

    /**
     * 父类会在请求进入拦截器后调用该方法，返回true则继续，返回false则会调用onAccessDenied()。这里在不通过时，还调用了isPermissive()方法，我们后面解释。
     * 是登录请求则允许通过,不是登录请求,需要执行登录
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 判断是否登录请求
        if (this.isLoginRequest(request, response)) {
            return true;
        }
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) {
            log.error("请求头中没有token或有误");
        } catch (Exception e) {
            log.error("请求头没有token或有误", e);
        }
        return allowed;
    }

    /**
     * 从登录请求头中获取token并执行登录操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 把请求头中的token转换成shiro认识的token类型
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            return onLoginFailure(token, null, request, response);
        }
        // 从请求头中拿到了token,进行登录认证...
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    /**
     * 获取请求头中的token和token对应的userId组合成shiro认识的token
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        // 从header中获取token
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String authorization = httpRequest.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            String userId = JwtUtils.getUserId(token);
            return new JwtToken(userId, token);
        } else {
            log.error("请求头中没有token");
            return null;
        }
    }


    /**
     * 如果这个Filter在之前isAccessAllowed（）方法中返回false,则会进入这个方法。我们这里直接返回错误的response
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String reqUri = ((HttpServletRequest) request).getRequestURI();
        log.info("请求接口: {} 鉴权失败", reqUri);
        ResponseUtil.response(
                (HttpServletResponse) response,
                200,
                JSON.toJSONString(new BaseOutDTO(null).fail(AuthError.AUTH_FAILED)));
        return false;
    }

    /**
     * 如果Shiro执行登录认证成功，会进入该方法，等同于用户名密码登录成功，这里还判断了是否要刷新Token
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }

    /**
     * 如果调用shiro的login认证失败，会回调这个方法，这里我们什么都不做，因为逻辑放到了onAccessDenied（）中。
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        // failResponse(request, (HttpServletResponse) response);
        return false;
    }

    /**
     * 失败响应
     */
    private void failResponse(ServletRequest request, HttpServletResponse response) {
        ResponseUtil.response(
                response,
                200,
                JSON.toJSONString(new BaseOutDTO(null).fail(AuthError.AUTH_FAILED)));
    }
}
