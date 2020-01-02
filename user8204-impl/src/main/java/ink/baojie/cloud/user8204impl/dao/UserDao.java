package ink.baojie.cloud.user8204impl.dao;

import ink.baojie.cloud.user8204api.entity.UserPO;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKey(UserPO record);
}
