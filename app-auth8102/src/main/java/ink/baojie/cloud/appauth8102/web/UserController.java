package ink.baojie.cloud.appauth8102.web;

import ink.baojie.cloud.appauth8102.domain.UserDomain;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.user8204api.entity.UserPO;
import ink.baojie.cloud.util.CheckUtil;
import ink.baojie.cloud.util.RequestIdUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author renbaojie
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserDomain userDomain;

    @PostMapping("save")
    public BaseOutDTO saveUser(@RequestBody UserPO userPO) {
        return userDomain.insertUser(RequestIdUtil.genRequestId(), userPO);
    }

    @GetMapping("delete/{userId}")
    public BaseOutDTO deleteUser(@PathVariable Integer userId) {
        CheckUtil.checkEmpty("userId", userId);
        return userDomain.deleteUser(RequestIdUtil.genRequestId(), userId);
    }

    @PostMapping("update")
    public BaseOutDTO updateUser(@RequestBody UserPO userPO) {
        return userDomain.updateUser(RequestIdUtil.genRequestId(), userPO);
    }

    @GetMapping("{userId}")
    public BaseOutDTO selectUser(@PathVariable Integer userId) {
        CheckUtil.checkEmpty("userId", userId);
        return userDomain.selectUserById(RequestIdUtil.genRequestId(), userId);
    }

    @GetMapping("phone")
    public BaseOutDTO selentUserByPhone(String phone) {
        CheckUtil.checkEmpty("phone", phone);
        return userDomain.selectUserByPhone(RequestIdUtil.genRequestId(), phone);
    }
}
