package ink.baojie.cloud.user8204impl.impl;

import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.exception.BaseError;
import ink.baojie.cloud.user8204api.entity.UserPO;
import ink.baojie.cloud.user8204api.service.UserService;
import ink.baojie.cloud.user8204impl.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author renbaojie
 */
@Slf4j
@Service
public class UserImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public BaseOutDTO insertUser(String requestId, UserPO userPO) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        Integer ret = userDao.insertSelective(userPO);
        if (ObjectUtils.isEmpty(ret) || ret == 0) {
            return outDTO.fail(new BaseError().nextError("请稍后重试"));
        }
        return outDTO;
    }

    @Override
    public BaseOutDTO deleteById(String requestId, Integer UserId) {
        return null;
    }

    @Override
    public BaseOutDTO updateUser(String requestId, UserPO userPO) {
        return null;
    }

    @Override
    public BaseOutDTO selectById(String requestId, Integer userId) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        UserPO userPO = userDao.selectByPrimaryKey(userId);

        if (ObjectUtils.isEmpty(userPO)) {
            return outDTO.fail(new BaseError().nextError("用户不存在"));
        }
        return outDTO.setData(userPO);
    }
}
