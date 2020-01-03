package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.entity.ActionPO;

import java.util.List;

public interface ActionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ActionPO record);

    int insertSelective(ActionPO record);

    ActionPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActionPO record);

    int updateByPrimaryKey(ActionPO record);

    /**
     * 查询用户所有权限(action)
     */
    List<ActionPO> selectAllActionByUserId(Integer userId);
}
