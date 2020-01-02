package ink.baojie.cloud.user8204api.service;

import ink.baojie.cloud.user8204api.entity.UserPO;

/**
 * @author renbaojie
 */
public interface UserService {

    UserPO selectById(Integer userId);
}
