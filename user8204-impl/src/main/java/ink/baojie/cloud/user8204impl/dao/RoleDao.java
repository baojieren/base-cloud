package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.entity.RolePO;

import java.util.List;

public interface RoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePO record);

    int insertSelective(RolePO record);

    RolePO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePO record);

    int updateByPrimaryKey(RolePO record);

    /**
     * 查询用户所有角色
     */
    List<RolePO> selectAllRoleByUserId(Integer userId);
}
