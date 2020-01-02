package ink.baojie.cloud.consumer8101.web;

import ink.baojie.cloud.consumer8101.base.Settings;
import ink.baojie.cloud.service.Provider8201Service;
import ink.baojie.cloud.service.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/csm")
public class Consumer8101Controller {
    @Reference
    private Provider8201Service provider8201Service;
    @Resource
    Settings settings;

    @GetMapping("test")
    public String hello(String msg) {
        log.info("消费者配置内容:{}", settings.getMsg());
        User user = new User();
        user.setName(msg);
        user.setAge(18);
        String zfc = provider8201Service.zfc("传过去的字符串", RandomUtils.nextInt());
        // String duixiang = provider8201Service.duixiang(user);
        return zfc + " / ";
    }

    @GetMapping("test2")
    public String hello2(String msg) {
        User user = new User();
        user.setName(msg);
        user.setAge(18);
        String duixiang = provider8201Service.duixiang(user);
        return duixiang;
    }
}
