package ink.baojie.cloud.user8204api.service;

import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.user8204api.dto.QueryUserDTO;
import ink.baojie.cloud.user8204api.entity.ActionPO;
import ink.baojie.cloud.user8204api.entity.RolePO;
import ink.baojie.cloud.user8204api.entity.UserPO;

import java.util.List;

/**
 * @author renbaojie
 */
public interface UserService {

    ResultBean<Integer> insertUser(String requestId, UserPO userPO);

    ResultBean deleteById(String requestId, Integer userId);

    ResultBean updateUser(String requestId, UserPO userPO);

    ResultBean<UserPO> selectById(String requestId, Integer userId);

    ResultBean<UserPO> selectByPhone(String requestId, String phone);

    /**
     * 查询用户所有角色
     */
    ResultBean<List<RolePO>> selectAllRoleByUserId(String requestId, Integer userId);

    /**
     * 查询用户所有权限(action)
     */
    ResultBean<List<ActionPO>> selectAllActionByUserId(String requestId, Integer userId);

    /**
     * 分页查询用户
     */
    ResultBean<List<UserPO>> selectPageUser(String requestId, QueryUserDTO queryUserDTO);
}
