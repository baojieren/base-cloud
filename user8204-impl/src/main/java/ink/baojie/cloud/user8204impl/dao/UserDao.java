package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.entity.UserPO;

public interface UserDao {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(UserPO record);

    Integer insertSelective(UserPO record);

    UserPO selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(UserPO record);

    Integer updateByPrimaryKey(UserPO record);
}
