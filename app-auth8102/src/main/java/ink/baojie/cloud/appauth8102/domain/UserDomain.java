package ink.baojie.cloud.appauth8102.domain;

import ink.baojie.cloud.appauth8102.base.AuthError;
import ink.baojie.cloud.appauth8102.base.AuthRuntimeException;
import ink.baojie.cloud.base.bean.BaseInPageDTO;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.bean.BaseOutPageDTO;
import ink.baojie.cloud.user8204api.bean.po.UserPo;
import ink.baojie.cloud.user8204api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author renbaojie
 */
@Slf4j
@Service
public class UserDomain {

    @Reference
    private UserService userService;

    public BaseOutDTO insertUser(String requestId, UserPo userPo) {
        Integer ret = userService.insertUser(requestId, userPo);
        if (ret <= 0) {
            log.error("插入失败");
            throw new AuthRuntimeException(new AuthError().nextError("插入失败"));
        }
        log.info("插入成功");
        return new BaseOutDTO(requestId);
    }

    public BaseOutDTO deleteUser(String requestId, Integer userId) {
        Integer ret = userService.deleteById(requestId, userId);
        if (ret <= 0) {
            log.error("删除失败");
            throw new AuthRuntimeException(new AuthError().nextError("删除失败"));
        }
        log.info("删除成功");
        return new BaseOutDTO(requestId);
    }

    public BaseOutDTO updateUser(String requestId, UserPo userPo) {
        Integer ret = userService.updateUser(requestId, userPo);
        if (ret <= 0) {
            log.error("更新失败");
            throw new AuthRuntimeException(new AuthError().nextError("更新失败"));
        }
        log.info("更新成功");
        return new BaseOutDTO(requestId);
    }

    public BaseOutDTO selectUserById(String requestId, Integer userId) {
        UserPo ret = userService.selectById(requestId, userId);
        if (ObjectUtils.isEmpty(ret)) {
            log.error("查询失败");
            throw new AuthRuntimeException(new AuthError().nextError("查询失败"));
        }
        return new BaseOutDTO(requestId).setData(ret);
    }

    public BaseOutDTO selectUserByPhone(String requestId, String phone) {
        UserPo ret = userService.selectByPhone(requestId, phone);
        if (ObjectUtils.isEmpty(ret)) {
            log.error("查询失败");
            throw new AuthRuntimeException(new AuthError().nextError("查询失败"));
        }
        return new BaseOutDTO(requestId).setData(ret);
    }

    public BaseOutPageDTO selectPageUser(String requestId, BaseInPageDTO inPageDTO, String phone) {
        return new BaseOutPageDTO(requestId);
    }
}
