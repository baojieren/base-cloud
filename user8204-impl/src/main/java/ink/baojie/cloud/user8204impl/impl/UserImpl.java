package ink.baojie.cloud.user8204impl.impl;

import ink.baojie.cloud.user8204api.entity.UserPO;
import ink.baojie.cloud.user8204api.service.UserService;
import ink.baojie.cloud.user8204impl.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public UserPO selectById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }
}
