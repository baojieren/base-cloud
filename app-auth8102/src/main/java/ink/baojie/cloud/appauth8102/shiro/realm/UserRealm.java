package ink.baojie.cloud.appauth8102.shiro.realm;

import ink.baojie.cloud.appauth8102.shiro.ShiroConfig;
import ink.baojie.cloud.user8204api.bean.po.ActionPo;
import ink.baojie.cloud.user8204api.bean.po.RolePo;
import ink.baojie.cloud.user8204api.bean.po.UserPo;
import ink.baojie.cloud.user8204api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 密码登录认证
 *
 * @author renbaojie
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Reference
    private UserService userService;

    /**
     * 决定是否进这个Realm
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
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
     * 密码登录认证, 该方法return null, shiro认为用户不存在
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String phone = (String) token.getPrincipal();
        log.info("手机号:{} 密码登录验证...", phone);

        //先查找用户
        UserPo userPo = userService.selectByPhone(phone);
        if (ObjectUtils.isEmpty(userPo)) {
            log.warn("手机号:" + phone + " 不存在");
            return null;
        }

        return new SimpleAuthenticationInfo(userPo.getId(), userPo.getPassword(), ByteSource.Util.bytes(ShiroConfig.SALT), getName());
    }
}
