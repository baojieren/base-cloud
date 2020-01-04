package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.entity.UserPO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<UserPO> {
    /**
     * 用手机号查询用户
     */
    UserPO selectOneByPhone(@Param("phone") String phone);
}
