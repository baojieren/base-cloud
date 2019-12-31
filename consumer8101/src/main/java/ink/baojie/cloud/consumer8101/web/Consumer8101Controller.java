package ink.baojie.cloud.consumer8101.web;

import ink.baojie.cloud.consumer8101.base.Settings;
import ink.baojie.cloud.service.Provider8201Service;
import lombok.extern.slf4j.Slf4j;
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
        return "消费者收到参数:" + msg + ",消费者配置内容: " + settings.getMsg() + " 提供者返回参数: " + provider8201Service.hello(msg);
    }

}
