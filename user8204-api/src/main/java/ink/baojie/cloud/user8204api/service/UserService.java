package ink.baojie.cloud.user8204api.service;

import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.user8204api.entity.UserPO;

/**
 * @author renbaojie
 */
public interface UserService {

    BaseOutDTO insertUser(String requestId, UserPO userPO);

    BaseOutDTO deleteById(String requestId, Integer UserId);

    BaseOutDTO updateUser(String requestId, UserPO userPO);

    BaseOutDTO selectById(String requestId, Integer userId);
}
