package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.bean.po.ActionPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
public interface ActionDao extends Mapper<ActionPo> {
    List<ActionPo> selectAllActionByUserId(@Param("userId") Integer userId);
}
