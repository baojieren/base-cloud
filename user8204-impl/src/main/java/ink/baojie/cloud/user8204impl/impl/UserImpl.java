package ink.baojie.cloud.user8204impl.impl;

import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.base.exception.BaseError;
import ink.baojie.cloud.base.exception.BaseRuntimeException;
import ink.baojie.cloud.user8204api.entity.UserPO;
import ink.baojie.cloud.user8204api.service.UserService;
import ink.baojie.cloud.user8204impl.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author renbaojie
 */
@Slf4j
@Service
public class UserImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public ResultBean insertUser(String requestId, UserPO userPO) {
        ResultBean resultBean = new ResultBean(requestId);
        Integer ret = userDao.insertSelective(userPO);
        if (ObjectUtils.isEmpty(ret) || ret == 0) {
            return resultBean.fail(new BaseError().nextError("请稍后重试"));
        }
        return resultBean;
    }

    @Override
    public ResultBean deleteById(String requestId, Integer UserId) {
        return null;
    }

    @Override
    public ResultBean updateUser(String requestId, UserPO userPO) {
        return null;
    }

    @Override
    public ResultBean selectById(String requestId, Integer userId) throws BaseRuntimeException {
        ResultBean resultBean = new ResultBean(requestId);
        return resultBean;
    }
}
