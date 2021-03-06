package ink.baojie.cloud.appauth8102.shiro.realm;

import com.alibaba.csp.sentinel.util.StringUtil;
import ink.baojie.cloud.appauth8102.shiro.token.JwtToken;
import ink.baojie.cloud.user8204api.bean.po.ActionPo;
import ink.baojie.cloud.user8204api.bean.po.RolePo;
import ink.baojie.cloud.user8204api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * token登录认证
 *
 * @author renbaojie
 */
@Slf4j
public class TokenRealm extends AuthorizingRealm {

    @Reference
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 决定是否进这个Realm
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        int userId = Integer.parseInt(principals.getPrimaryPrincipal().toString());
        log.info("用户id:{} 进行权限验证", userId);

        //查找用户所有角色
        List<RolePo> allRoleByUserId = userService.selectAllRoleByUserId(userId);
        if (!ObjectUtils.isEmpty(allRoleByUserId)) {
            Set<String> roles = allRoleByUserId.stream().map(RolePo::getRoleTag).collect(Collectors.toSet());
            authorizationInfo.setRoles(roles);
        }

        //查找用户所有action
        List<ActionPo> allActionByUserId = userService.selectAllActionByUserId(userId);

        if (ObjectUtils.isEmpty(allActionByUserId)) {
            Set<String> actions = allActionByUserId.stream().map(ActionPo::getActionTag).collect(Collectors.toSet());
            authorizationInfo.setStringPermissions(actions);
        }

        return authorizationInfo;
    }

    /**
     * token登录认证, 该方法return null, shiro认为用户不存在
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String userId = (String) jwtToken.getPrincipal();
        log.info("用户id:{} 进行token验证...", userId);

        // 使用userId找token，不存在代表过期
        String userToken = stringRedisTemplate.opsForValue().get("token:" + userId);
        if (StringUtil.isEmpty(userToken)) {
            log.warn("用户id:{} token不存在", userId);
            return null;
        }

        log.info("用户id:{} token存在", userId);
        return new SimpleAuthenticationInfo(userId, userToken, getName());
    }
}
