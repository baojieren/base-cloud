package ink.baojie.cloud.consumer8101.web;

import ink.baojie.cloud.base.dto.BaseOutDTO;
import ink.baojie.cloud.user8204api.service.UserService;
import ink.baojie.cloud.util.RequestIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/csm")
public class Consumer8101Controller {
    @Reference
    UserService userService;

    @GetMapping("test")
    public BaseOutDTO hello(Integer userId) {
        String requestId = RequestIdUtil.createId();
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        BaseOutDTO result = userService.selectById(requestId, userId);
        outDTO.setData(result.getData());
        return outDTO;
    }

    // @GetMapping("test2")
    // public String hello2(String msg) {
    //     User user = new User();
    //     user.setName(msg);
    //     user.setAge(18);
    //     String duixiang = provider8201Service.duixiang(user);
    //     return duixiang;
    // }
}
