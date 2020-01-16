package ink.baojie.cloud.appauth8102.web;

import ink.baojie.cloud.appauth8102.domain.AuthDomain;
import ink.baojie.cloud.appauth8102.bean.dto.LoginDTO;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.util.CheckUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author renbaojie
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthDomain authDomain;

    /**
     * 手机号+密码登录
     */
    @PostMapping("login")
    public BaseOutDTO login(@RequestBody LoginDTO loginDTO) {
        CheckUtil.checkEmpty("phone", loginDTO.getPhone());
        CheckUtil.checkEmpty("password", loginDTO.getPassword());
        return authDomain.doLogin(loginDTO);
    }

    /**
     * 手机号+密码注册
     */
    @PostMapping("signup")
    public BaseOutDTO signUp(@RequestBody LoginDTO loginDTO) {
        CheckUtil.checkEmpty("phone", loginDTO.getPhone());
        CheckUtil.checkEmpty("password", loginDTO.getPassword());
        return authDomain.signUp(loginDTO);
    }
}
