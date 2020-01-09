package ink.baojie.cloud.user8204impl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.baojie.cloud.user8204api.bean.po.RolePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
public interface RoleDao extends BaseMapper<RolePo> {
    List<RolePo> selectAllRoleByUserId(@Param("userId") Integer userId);
}
