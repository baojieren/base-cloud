package ink.baojie.cloud.appauth8102.domain;

import com.alibaba.fastjson.JSONObject;
import ink.baojie.cloud.appauth8102.base.AuthError;
import ink.baojie.cloud.appauth8102.base.AuthRuntimeException;
import ink.baojie.cloud.base.bean.BaseInPageDTO;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.bean.BaseOutPageDTO;
import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.user8204api.dto.QueryUserDTO;
import ink.baojie.cloud.user8204api.entity.UserPO;
import ink.baojie.cloud.user8204api.exception.UserError;
import ink.baojie.cloud.user8204api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author renbaojie
 */
@Slf4j
@Service
public class UserDomain {

    @Reference
    private UserService userService;

    public BaseOutDTO insertUser(String requestId, UserPO userPO) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);

        // 先查看手机号是否已注册
        ResultBean<UserPO> selectByPhone = userService.selectByPhone(requestId, userPO.getPhone());

        if (!selectByPhone.isSuccess()) {
            throw new AuthRuntimeException(AuthError.DB_ERR);
        }

        if (!ObjectUtils.isEmpty(selectByPhone.getData())) {
            return outDTO.fail(new AuthError().nextError("手机号已注册"));
        }

        ResultBean<Integer> resultBean = userService.insertUser(requestId, userPO);
        if (resultBean.isSuccess()) {
            log.info("添加成功 用户id:{}", resultBean.getData());
            return outDTO.setData(resultBean.getData());
        } else {
            log.error("添加失败");
            return outDTO.fail(new AuthError().nextError("操作失败"));
        }
    }

    public BaseOutDTO deleteUser(String requestId, Integer userId) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        ResultBean resultBean = userService.deleteById(requestId, userId);
        if (resultBean.isSuccess()) {
            log.info("删除用户:{} 成功", userId);
            return outDTO;
        } else {
            log.error("删除用户:{} 失败", userId);
            return outDTO.fail(new UserError().nextError(resultBean.getMsg()));
        }
    }

    public BaseOutDTO updateUser(String requestId, UserPO userPO) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        ResultBean resultBean = userService.updateUser(requestId, userPO);
        if (resultBean.isSuccess()) {
            log.info("更新用户:{} 成功", userPO.getId());
            return outDTO;
        } else {
            log.error("更新用户:{} 失败", userPO.getId());
            return outDTO.fail(new AuthError().nextError("更新失败"));
        }
    }

    public BaseOutDTO selectUserById(String requestId, Integer userId) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        ResultBean resultBean = userService.selectById(requestId, userId);
        return outDTO.setData(resultBean.getData());
    }

    public BaseOutDTO selectUserByPhone(String requestId, String phone) {
        BaseOutDTO outDTO = new BaseOutDTO(requestId);
        ResultBean resultBean = userService.selectByPhone(requestId, phone);
        return outDTO.setData(resultBean.getData());
    }

    public BaseOutPageDTO selectPageUser(String requestId, BaseInPageDTO inPageDTO, String phone) {
        QueryUserDTO queryUserDTO = new QueryUserDTO();
        queryUserDTO.pageNum = inPageDTO.getPageNum();
        queryUserDTO.pageSize = inPageDTO.getPageSize();
        queryUserDTO.phone = phone;
        ResultBean<List<UserPO>> listResultBean = userService.selectPageUser(requestId, queryUserDTO);
        log.info(JSONObject.toJSONString(listResultBean.getData()));
        return new BaseOutPageDTO(requestId);
    }
}
