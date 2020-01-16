package ink.baojie.cloud.appauth8102.shiro.filter;

import com.alibaba.fastjson.JSON;
import ink.baojie.cloud.appauth8102.base.AuthError;
import ink.baojie.cloud.appauth8102.util.ResponseUtil;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 权限判断
 *
 * @author renbaojie
 */
@Slf4j
public class CustomRolesAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 当前接口需要的角色列表
        String[] rolesArray = (String[]) mappedValue;
        String reqUri = ((HttpServletRequest) request).getRequestURI();

        // 没有角色限制，有权限访问
        if (rolesArray == null || rolesArray.length == 0) {
            log.info("接口:{} 无角色限制，允许访问", reqUri);
            return true;
        } else {
            log.info("接口:{} 需要角色:{}", reqUri, Arrays.toString(rolesArray));
        }

        // 若当前用户是rolesArray中的任何一个，则有权限访问
        Subject subject = getSubject(request, response);
        for (String role : rolesArray) {
            if (subject.hasRole(role)) {
                log.info("用户id:{} 有:{} 角色，允许访问", subject.getPrincipal(), role);
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        String reqUri = ((HttpServletRequest) request).getRequestURI();
        Subject subject = getSubject(request, response);
        log.info("用户id:{} 接口:{} 权限不足，拒绝访问", subject.getPrincipal().toString(), reqUri);
        ResponseUtil.response(
                (HttpServletResponse) response,
                200,
                JSON.toJSONString(new BaseOutDTO().fail(AuthError.REJECT)));
        return false;
    }

}
