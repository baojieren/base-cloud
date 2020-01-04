package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.entity.ActionPO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ActionDao extends Mapper<ActionPO> {
    /**
     * 查询用户所有权限(action)
     */
    List<ActionPO> selectAllActionByUserId(Integer userId);
}
