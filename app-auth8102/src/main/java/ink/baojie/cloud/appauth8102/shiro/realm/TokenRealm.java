package ink.baojie.cloud.appauth8102.shiro.realm;

import com.alibaba.csp.sentinel.util.StringUtil;
import ink.baojie.cloud.appauth8102.shiro.ShiroConfig;
import ink.baojie.cloud.appauth8102.shiro.token.JwtToken;
import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.user8204api.entity.ActionPO;
import ink.baojie.cloud.user8204api.entity.RolePO;
import ink.baojie.cloud.user8204api.entity.UserPO;
import ink.baojie.cloud.user8204api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        int userId = Integer.parseInt(principals.getPrimaryPrincipal().toString());
        log.info("用户id:{} 进行权限验证", userId);

        Set<String> roles = new HashSet<>();
        Set<String> actions = new HashSet<>();

        //查找用户所有角色
        ResultBean<List<RolePO>> allRoleByUserId = userService.selectAllRoleByUserId(null, userId);
        if (!ObjectUtils.isEmpty(allRoleByUserId.getData())) {
            for (RolePO rolePO : allRoleByUserId.getData()) {
                roles.add(rolePO.getRoleTag());
            }
        }

        //查找用户所有action
        ResultBean<List<ActionPO>> allActionByUserId = userService.selectAllActionByUserId(null, userId);
        if (!ObjectUtils.isEmpty(allActionByUserId.getData())) {
            for (ActionPO actionPO : allActionByUserId.getData()) {
                actions.add(actionPO.getActionTag());
            }
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(actions);
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
            log.error("用户id:{} token不存在", userId);
            return null;
        }

        log.info("用户id:{} token存在", userId);
        return new SimpleAuthenticationInfo(userId, userToken, getName());
    }
}
