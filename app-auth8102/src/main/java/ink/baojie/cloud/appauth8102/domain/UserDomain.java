package ink.baojie.cloud.appauth8102.domain;

import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.exception.BaseRuntimeException;
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

    public BaseOutDTO getUser(String requestId, Integer userId) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        try {
            userService.selectById(requestId, userId);
        } catch (BaseRuntimeException e) {
            log.info("自定义异常");
        } catch (RuntimeException re) {
            log.info("系统异常异常");
        }
        return outDTO;
    }
}
