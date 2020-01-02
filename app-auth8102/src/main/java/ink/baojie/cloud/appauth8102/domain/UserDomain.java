package ink.baojie.cloud.appauth8102.domain;

import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.bean.ResultBean;
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
        ResultBean resultBean = userService.selectById(requestId, userId);
        return outDTO.setData(resultBean.getData());
    }
}
