package ink.baojie.cloud.appauth8102.domain;

import ink.baojie.cloud.appauth8102.base.AuthError;
import ink.baojie.cloud.appauth8102.base.AuthRuntimeException;
import ink.baojie.cloud.appauth8102.bean.bo.RoleBo;
import ink.baojie.cloud.appauth8102.bean.dto.LoginDTO;
import ink.baojie.cloud.appauth8102.bean.vo.LoginSuccessVO;
import ink.baojie.cloud.appauth8102.shiro.ShiroConfig;
import ink.baojie.cloud.appauth8102.util.JwtUtils;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.user8204api.bean.po.RolePo;
import ink.baojie.cloud.user8204api.bean.po.UserPo;
import ink.baojie.cloud.user8204api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author renbaojie
 */
@Slf4j
@Service
public class AuthDomain {
    @Reference
    UserService userService;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 用户名和密码登录
     */
    public BaseOutDTO doLogin(String requestId, LoginDTO loginDTO) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        try {
            //登陆验证 创建UsernamePasswordToken, 使进入UserRealm
            UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getPhone(), loginDTO.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            log.info("手机号:{} 登录成功", loginDTO.getPhone());
        } catch (UnknownAccountException e) {
            return outDTO.fail(AuthError.USER_NOT_EXIST);
        } catch (AuthenticationException e) {
            return outDTO.fail(AuthError.ERR_PASSWORD);
        }

        UserPo userPo = userService.selectByPhone(requestId, loginDTO.getPhone());

        List<RolePo> rolePos = userService.selectAllRoleByUserId(requestId, userPo.getId());

        List<RoleBo> roleBos = new ArrayList<>();
        RoleBo roleBo;
        for (RolePo rolePo : rolePos) {
            roleBo = new RoleBo();
            roleBo.setRoleName(rolePo.getRoleName());
            roleBo.setRoleTag(rolePo.getRoleTag());
            roleBos.add(roleBo);
        }

        String[] roleArr = roleBos.stream().map(RoleBo::getRoleTag).toArray(String[]::new);
        LoginSuccessVO vo = new LoginSuccessVO();
        vo.setUserId(userPo.getId());
        vo.setUserName(userPo.getUserName());
        vo.setPhone(userPo.getPhone());
        vo.setAvatar(userPo.getAvatar());
        String token = JwtUtils.sign(userPo.getId().toString(), roleArr, ShiroConfig.SALT);
        stringRedisTemplate.opsForValue().set("token:" + userPo.getId(), token, 7, TimeUnit.DAYS);
        vo.setToken(token);
        vo.setRoleList(roleBos);
        outDTO.setData(vo);
        return outDTO;
    }

    /**
     * 注册
     */
    public BaseOutDTO signUp(String requestId, LoginDTO loginDTO) {
        // 检查手机号是否已经注册
        UserPo selectByPhone = userService.selectByPhone(requestId, loginDTO.getPhone());
        if (!ObjectUtils.isEmpty(selectByPhone)) {
            throw new AuthRuntimeException(new AuthError().nextError("手机号已被注册"));
        }
        UserPo userPo = new UserPo();
        userPo.setUserName(loginDTO.getPhone());
        userPo.setPhone(loginDTO.getPhone());
        userPo.setPassword(new SimpleHash("md5", loginDTO.getPassword(), ByteSource.Util.bytes(ShiroConfig.SALT), 1024).toString());
        userService.insertUser(requestId, userPo);
        return new BaseOutDTO(requestId);
    }
}
