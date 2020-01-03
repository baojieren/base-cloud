package ink.baojie.cloud.appauth8102.domain;

import ink.baojie.cloud.appauth8102.entity.LoginSuccessBO;
import ink.baojie.cloud.appauth8102.entity.RoleBO;
import ink.baojie.cloud.appauth8102.entity.dto.LoginDTO;
import ink.baojie.cloud.appauth8102.shiro.ShiroConfig;
import ink.baojie.cloud.appauth8102.util.JwtUtils;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.base.exception.BaseError;
import ink.baojie.cloud.base.exception.BaseRuntimeException;
import ink.baojie.cloud.user8204api.entity.RolePO;
import ink.baojie.cloud.user8204api.entity.UserPO;
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
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    /**
     * 用户名和密码登录
     */
    public BaseOutDTO doLogin(String requestId, LoginDTO loginDTO) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        try {
            //登陆验证
            UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getPhone(), loginDTO.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            log.info("手机号:{} 登录成功", loginDTO.getPhone());
        } catch (UnknownAccountException e) {
            return outDTO.fail(BaseError.USER_NOT_EXIST);
        } catch (AuthenticationException e) {
            return outDTO.fail(BaseError.ERR_PASSWORD);
        }

        ResultBean<UserPO> selectByPhone = userService.selectByPhone(requestId, loginDTO.getPhone());
        UserPO userPO = selectByPhone.getData();
        List<RoleBO> roleBOList = new ArrayList<>();

        ResultBean<List<RolePO>> allRoleByUserId = userService.selectAllRoleByUserId(requestId, userPO.getId());


        if (!ObjectUtils.isEmpty(allRoleByUserId.getData())) {
            RoleBO roleBO;
            for (RolePO rolePO : allRoleByUserId.getData()) {
                roleBO = new RoleBO();
                roleBO.setRoleName(rolePO.getRoleName());
                roleBO.setRoleTag(rolePO.getRoleTag());
                roleBOList.add(roleBO);
            }
        }
        LoginSuccessBO bo = new LoginSuccessBO();
        bo.setUserId(userPO.getId());
        bo.setUserName(userPO.getUserName());
        bo.setPhone(userPO.getPhone());
        bo.setAvatar(userPO.getAvatar());
        String token = JwtUtils.sign(userPO.getId().toString(), "sAlT");
        // stringRedisTemplate.opsForValue().set("token:" + authPO.getId(), token, 365, TimeUnit.DAYS);
        bo.setToken(token);
        bo.setRoleList(roleBOList);
        outDTO.setData(bo);
        return outDTO;
    }

    /**
     * 注册
     */
    public BaseOutDTO signUp(String requestId, LoginDTO loginDTO) {
        // 检查手机号是否已经注册
        ResultBean<UserPO> selectByPhone = userService.selectByPhone(requestId, loginDTO.getPhone());
        if (selectByPhone.getData() != null) {
            throw new BaseRuntimeException(new BaseError().nextError("手机号已被注册"));
        }

        UserPO userPO = new UserPO();
        userPO.setUserName(loginDTO.getPhone());
        userPO.setPhone(loginDTO.getPhone());
        userPO.setPassword(new SimpleHash("md5", loginDTO.getPassword(), ByteSource.Util.bytes(ShiroConfig.SALT), 1024).toString());
        userService.insertUser(requestId, userPO);

        return new BaseOutDTO(requestId);
    }
}
