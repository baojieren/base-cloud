package ink.baojie.cloud.user8204api.service;

import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.base.exception.BaseRuntimeException;
import ink.baojie.cloud.user8204api.entity.ActionPO;
import ink.baojie.cloud.user8204api.entity.RolePO;
import ink.baojie.cloud.user8204api.entity.UserPO;

import java.util.List;

/**
 * @author renbaojie
 */
public interface UserService {

    ResultBean insertUser(String requestId, UserPO userPO);

    ResultBean deleteById(String requestId, Integer UserId);

    ResultBean updateUser(String requestId, UserPO userPO);

    ResultBean<UserPO> selectById(String requestId, Integer userId) throws BaseRuntimeException;

    ResultBean<UserPO> selectByPhone(String requestId, String phone) throws BaseRuntimeException;

    /**
     * 查询用户所有角色
     */
    ResultBean<List<RolePO>> selectAllRoleByUserId(String requestId, Integer userId) throws BaseRuntimeException;

    /**
     * 查询用户所有权限(action)
     */
    ResultBean<List<ActionPO>> selectAllActionByUserId(String requestId, Integer userId) throws BaseRuntimeException;
}
