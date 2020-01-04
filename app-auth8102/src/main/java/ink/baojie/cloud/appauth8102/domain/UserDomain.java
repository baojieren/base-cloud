package ink.baojie.cloud.appauth8102.domain;

import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.base.exception.BaseError;
import ink.baojie.cloud.user8204api.entity.UserPO;
import ink.baojie.cloud.user8204api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author renbaojie
 */
@Slf4j
@Service
public class UserDomain {

    @Reference
    private UserService userService;

    public BaseOutDTO insertUser(String requestId, UserPO userPO) {
        ResultBean resultBean = userService.insertUser(requestId, userPO);
        if (resultBean.isSuccess()) {
            log.info("添加成功");
        } else {
            log.error("添加失败");
        }
        return new BaseOutDTO(requestId);
    }

    public BaseOutDTO deleteUser(String requestId, Integer userId) {
        ResultBean resultBean = userService.deleteById(requestId, userId);
        if (resultBean.isSuccess()) {
            log.info("删除用户:{} 成功", userId);
        } else {
            log.error("删除用户:{} 失败", userId);
        }
        return new BaseOutDTO(requestId);
    }

    public BaseOutDTO updateUser(String requestId, UserPO userPO) {
        ResultBean resultBean = userService.updateUser(requestId, userPO);
        if (resultBean.isSuccess()) {
            log.info("更新用户:{} 成功", userPO.getId());
        } else {
            log.error("更新用户:{} 失败", userPO.getId());
        }
        return new BaseOutDTO(requestId);
    }

    public BaseOutDTO selectUserById(String requestId, Integer userId) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        ResultBean resultBean = userService.selectById(requestId, userId);
        if (resultBean.isSuccess()) {
            return outDTO.setData(resultBean.getData());
        } else {
            return outDTO.fail(BaseError.USER_NOT_EXIST);
        }
    }

    public BaseOutDTO selectUserByPhone(String requestId, String phone) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        ResultBean resultBean = userService.selectByPhone(requestId, phone);
        if (resultBean.isSuccess()) {
            return outDTO.setData(resultBean.getData());
        } else {
            return outDTO.fail(BaseError.USER_NOT_EXIST);
        }
    }
}
