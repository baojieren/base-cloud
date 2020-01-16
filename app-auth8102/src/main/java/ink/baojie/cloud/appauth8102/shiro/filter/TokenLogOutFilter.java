package ink.baojie.cloud.appauth8102.shiro.filter;

import com.alibaba.fastjson.JSON;
import ink.baojie.cloud.appauth8102.util.ResponseUtil;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出登录
 *
 * @author renbaojie
 */
@Slf4j
public class TokenLogOutFilter extends PathMatchingFilter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public TokenLogOutFilter(String logoutPath) {
        super();
        this.appliedPaths.put(logoutPath, null);
    }

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        int userId = Integer.parseInt(subject.getPrincipal().toString());
        log.info("用户id:{} 退出登录...", userId);
        try {
            subject.logout();
        } catch (SessionException ise) {
            log.error("用户id:{} 退出登录异常:{}", ise);
        } finally {
            stringRedisTemplate.delete("token:" + userId);
            log.info("用户id:{} token已删除", userId);
        }
        ResponseUtil.response((HttpServletResponse) response, 200, JSON.toJSONString(new BaseOutDTO()));
        return false;
    }
}
