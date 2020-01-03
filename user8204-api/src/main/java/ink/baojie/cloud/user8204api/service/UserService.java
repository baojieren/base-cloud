package ink.baojie.cloud.user8204api.service;

import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.base.exception.BaseRuntimeException;
import ink.baojie.cloud.user8204api.entity.UserPO;

/**
 * @author renbaojie
 */
public interface UserService {

    ResultBean insertUser(String requestId, UserPO userPO);

    ResultBean deleteById(String requestId, Integer UserId);

    ResultBean updateUser(String requestId, UserPO userPO);

    ResultBean selectById(String requestId, Integer userId) throws BaseRuntimeException;
}
