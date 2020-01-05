package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.entity.UserPO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<UserPO> {
    /**
     * 用手机号查询用户
     */
    UserPO selectOneByPhone(@Param("phone") String phone);

    /**
     * 分页查询用户
     */
    List<UserPO> selectPageUser(@Param("phone") String phone);

}
