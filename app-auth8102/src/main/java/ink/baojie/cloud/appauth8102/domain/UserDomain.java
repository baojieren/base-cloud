package ink.baojie.cloud.appauth8102.domain;

import ink.baojie.cloud.base.dto.BaseOutDTO;
import ink.baojie.cloud.user8204api.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author renbaojie
 */
@Service
public class UserDomain {

    @Reference
    private UserService userService;

    public BaseOutDTO getUser(String requestId,Integer userId){
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        BaseOutDTO rst = userService.selectById(requestId, userId);
        return outDTO.setData(rst.getData());
    }
}
