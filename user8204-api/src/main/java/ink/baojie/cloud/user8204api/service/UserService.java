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

    Integer insertUser(UserPo userPo);

    Integer deleteById(Integer userId);

    Integer updateUser(UserPo userPo);

    UserPo selectById(Integer userId);

    UserPo selectByPhone(String phone);

    /**
     * 查询用户所有角色
     */
    List<RolePo> selectAllRoleByUserId(Integer userId);

    /**
     * 查询用户所有权限(action)
     */
    List<ActionPo> selectAllActionByUserId(Integer userId);

    /**
     * 分页查询用户
     */
    PageData selectPageUser(QueryUserDTO queryUserDTO);
}
