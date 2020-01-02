package ink.baojie.cloud.appauth8102.web;

import ink.baojie.cloud.appauth8102.entity.dto.LoginDTO;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.util.CheckUtil;
import ink.baojie.cloud.util.RequestIdUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author renbaojie
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("login")
    public BaseOutDTO login(@RequestBody LoginDTO loginDTO){
        CheckUtil.checkEmpty("userName",loginDTO.getUserName());
        CheckUtil.checkEmpty("password",loginDTO.getPassword());
        return new BaseOutDTO(RequestIdUtil.genRequestId());
    }
}
