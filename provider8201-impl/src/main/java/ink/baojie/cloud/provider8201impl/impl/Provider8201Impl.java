package ink.baojie.cloud.provider8201impl.impl;

import ink.baojie.cloud.provider8201impl.base.BaseConfig;
import ink.baojie.cloud.service.Provider8201Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class Provider8201Impl implements Provider8201Service {

    @Resource
    BaseConfig baseConfig;

    @Override
    public String hello(String msg) {
        return "提供者收到参数: " + msg + " 提供者配置信息: " + baseConfig.getMsg();
    }
}
