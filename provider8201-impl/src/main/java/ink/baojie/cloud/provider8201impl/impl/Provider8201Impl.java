package ink.baojie.cloud.provider8201impl.impl;

import ink.baojie.cloud.provider8201impl.base.Settings;
import ink.baojie.cloud.service.Provider8201Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class Provider8201Impl implements Provider8201Service {

    @Resource
    Settings settings;

    @Override
    public String hello(String msg) {
        log.info("提供者配置内容:{}", settings.getMsg());
        return "提供者收到参数: " + msg + " 提供者配置信息: " + settings.getMsg();
    }
}
