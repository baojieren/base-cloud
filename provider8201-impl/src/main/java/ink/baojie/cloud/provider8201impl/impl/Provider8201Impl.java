package ink.baojie.cloud.provider8201impl.impl;

import ink.baojie.cloud.provider8201impl.base.Settings;
import ink.baojie.cloud.service.Provider8201Service;
import ink.baojie.cloud.service.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class Provider8201Impl implements Provider8201Service {

    @Resource
    Settings settings;

    @Override
    public String zfc(String str, Integer shuzhi) {
        return "zfc返回内容";
    }

    @Override
    public String duixiang(User user) {
        return "对象返回内容";
    }
}
