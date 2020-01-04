package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.entity.RolePO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleDao extends Mapper<RolePO> {
    /**
     * 查询用户所有角色
     */
    List<RolePO> selectAllRoleByUserId(Integer userId);
}
