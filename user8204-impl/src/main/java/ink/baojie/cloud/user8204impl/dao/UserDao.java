package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.bean.po.UserPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
public interface UserDao extends Mapper<UserPo> {
    UserPo selectOneByPhone(@Param("phone")String phone);

    List<UserPo> selectUserByPage(String phone);
}
