package ink.baojie.cloud.appauth8102.web;

import ink.baojie.cloud.appauth8102.domain.UserDomain;
import ink.baojie.cloud.base.bean.BaseInPageDTO;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.bean.BaseOutPageDTO;
import ink.baojie.cloud.user8204api.bean.po.UserPo;
import ink.baojie.cloud.util.CheckUtil;
import ink.baojie.cloud.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author renbaojie
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserDomain userDomain;

    @PostMapping("save")
    public BaseOutDTO saveUser(@RequestBody UserPo userPo) {
        return userDomain.insertUser(userPo);
    }

    @GetMapping("delete/{userId}")
    public BaseOutDTO deleteUser(@PathVariable Integer userId) {
        CheckUtil.checkEmpty("userId", userId);
        return userDomain.deleteUser(userId);
    }

    @PostMapping("update")
    public BaseOutDTO updateUser(@RequestBody UserPo userPo) {
        return userDomain.updateUser(userPo);
    }

    @GetMapping("{userId}")
    public BaseOutDTO selectUser(@PathVariable Integer userId) {
        CheckUtil.checkEmpty("userId", userId);
        return userDomain.selectUserById(userId);
    }

    @GetMapping("phone")
    public BaseOutDTO selentUserByPhone(String phone) {
        CheckUtil.checkEmpty("phone", phone);
        return userDomain.selectUserByPhone(phone);
    }

    @GetMapping("page")
    public BaseOutPageDTO selentPageUserByPhone(Integer pageNum, Integer pageSize, String phone) {
        CheckUtil.checkEmpty("phone", phone);
        BaseInPageDTO inPageDTO = new BaseInPageDTO();
        inPageDTO.setPageNum(pageNum);
        inPageDTO.setPageSize(pageSize);
        return userDomain.selectPageUser(inPageDTO, phone);
    }
}
