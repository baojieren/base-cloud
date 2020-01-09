package ink.baojie.cloud.user8204impl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.baojie.cloud.user8204api.bean.po.UserPo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
public interface UserDao extends BaseMapper<UserPo> {
    UserPo selectOneByPhone(@Param("phone")String phone);
}
