package ink.baojie.cloud.user8204impl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.baojie.cloud.user8204api.bean.po.ActionPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
public interface ActionDao extends BaseMapper<ActionPo> {
    List<ActionPo> selectAllActionByUserId(@Param("userId") Integer userId);
}
