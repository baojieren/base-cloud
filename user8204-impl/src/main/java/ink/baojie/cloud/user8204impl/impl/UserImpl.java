package ink.baojie.cloud.user8204impl.impl;

import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.base.exception.BaseError;
import ink.baojie.cloud.base.exception.BaseRuntimeException;
import ink.baojie.cloud.user8204api.entity.ActionPO;
import ink.baojie.cloud.user8204api.entity.RolePO;
import ink.baojie.cloud.user8204api.entity.UserPO;
import ink.baojie.cloud.user8204api.service.UserService;
import ink.baojie.cloud.user8204impl.dao.ActionDao;
import ink.baojie.cloud.user8204impl.dao.RoleDao;
import ink.baojie.cloud.user8204impl.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author renbaojie
 */
@Slf4j
@Service
public class UserImpl implements UserService {
    @Resource
    UserDao userDao;
    @Resource
    RoleDao roleDao;
    @Resource
    ActionDao actionDao;

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
    public ResultBean<UserPO> selectById(String requestId, Integer userId) throws BaseRuntimeException {
        UserPO userPO = userDao.selectByPrimaryKey(userId);
        return new ResultBean<UserPO>(requestId).setData(userPO);
    }

    @Override
    public ResultBean<UserPO> selectByPhone(String requestId, String phone) throws BaseRuntimeException {
        UserPO userPO = userDao.selectOneByPhone(phone);
        return new ResultBean<UserPO>(requestId).setData(userPO);
    }

    @Override
    public ResultBean<List<RolePO>> selectAllRoleByUserId(String requestId, Integer userId) throws BaseRuntimeException {
        List<RolePO> rolePOS = roleDao.selectAllRoleByUserId(userId);
        return new ResultBean<List<RolePO>>(requestId).setData(rolePOS);
    }

    @Override
    public ResultBean<List<ActionPO>> selectAllActionByUserId(String requestId, Integer userId) throws BaseRuntimeException {
        List<ActionPO> actionPOS = actionDao.selectAllActionByUserId(userId);
        return new ResultBean<List<ActionPO>>(requestId).setData(actionPOS);
    }
}
