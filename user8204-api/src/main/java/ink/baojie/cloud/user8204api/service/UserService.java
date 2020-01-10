package ink.baojie.cloud.user8204api.service;

import ink.baojie.cloud.base.bean.PageData;
import ink.baojie.cloud.user8204api.bean.dto.QueryUserDTO;
import ink.baojie.cloud.user8204api.bean.po.ActionPo;
import ink.baojie.cloud.user8204api.bean.po.RolePo;
import ink.baojie.cloud.user8204api.bean.po.UserPo;

import java.util.List;

/**
 * @author renbaojie
 */
public interface UserService {

    Integer insertUser(String requestId, UserPo userPo);

    Integer deleteById(String requestId, Integer userId);

    Integer updateUser(String requestId, UserPo userPo);

    UserPo selectById(String requestId, Integer userId);

    UserPo selectByPhone(String requestId, String phone);

    /**
     * 查询用户所有角色
     */
    List<RolePo> selectAllRoleByUserId(String requestId, Integer userId);

    /**
     * 查询用户所有权限(action)
     */
    List<ActionPo> selectAllActionByUserId(String requestId, Integer userId);

    /**
     * 分页查询用户
     */
    PageData selectPageUser(String requestId, QueryUserDTO queryUserDTO);
}
