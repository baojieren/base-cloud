package ink.baojie.cloud.appauth8102.web;

import ink.baojie.cloud.appauth8102.domain.UserDomain;
import ink.baojie.cloud.base.dto.BaseOutDTO;
import ink.baojie.cloud.util.CheckUtil;
import ink.baojie.cloud.util.RequestIdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author renbaojie
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserDomain userDomain;

    @GetMapping("/{userId}")
    public BaseOutDTO getUser(@PathVariable Integer userId) {
        CheckUtil.checkEmpty("userId", userId);
        String requestId = RequestIdUtil.genRequestId();
        return userDomain.getUser(requestId, userId);
    }
}
