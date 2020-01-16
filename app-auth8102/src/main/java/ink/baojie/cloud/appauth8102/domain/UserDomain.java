package ink.baojie.cloud.appauth8102.domain;

import ink.baojie.cloud.appauth8102.base.AuthError;
import ink.baojie.cloud.appauth8102.base.AuthRuntimeException;
import ink.baojie.cloud.base.bean.BaseInPageDTO;
import ink.baojie.cloud.base.bean.BaseOutDTO;
import ink.baojie.cloud.base.bean.BaseOutPageDTO;
import ink.baojie.cloud.base.bean.PageData;
import ink.baojie.cloud.tx8205api.service.TxService;
import ink.baojie.cloud.user8204api.bean.dto.QueryUserDTO;
import ink.baojie.cloud.user8204api.bean.po.UserPo;
import ink.baojie.cloud.user8204api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @author renbaojie
 */
@Slf4j
@Service
public class UserDomain {

    @Reference
    private UserService userService;
    @Reference
    private TxService txService;

    @Transactional
    public BaseOutDTO insertUser( UserPo userPo) {
        Integer ret = userService.insertUser(userPo);
        if (ret <= 0) {
            log.error("user插入失败");
            throw new AuthRuntimeException(new AuthError().nextError("user插入失败"));
        }
        log.info("user插入成功");

        String ret2 = txService.saveTx(userPo.getPhone());
        log.info("事务工程返回:{}", ret2);

        if ("111111".equals(userPo.getPassword())) {
            int a = 1 / 0;
        }
        return new BaseOutDTO();
    }

    public BaseOutDTO deleteUser( Integer userId) {
        Integer ret = userService.deleteById(userId);
        if (ret <= 0) {
            log.error("删除失败");
            throw new AuthRuntimeException(new AuthError().nextError("删除失败"));
        }
        log.info("删除成功");
        return new BaseOutDTO();
    }

    public BaseOutDTO updateUser( UserPo userPo) {
        Integer ret = userService.updateUser(userPo);
        if (ret <= 0) {
            log.error("更新失败");
            throw new AuthRuntimeException(new AuthError().nextError("更新失败"));
        }
        log.info("更新成功");
        return new BaseOutDTO();
    }

    public BaseOutDTO selectUserById( Integer userId) {
        UserPo ret = userService.selectById(userId);
        if (ObjectUtils.isEmpty(ret)) {
            log.error("查询失败");
            throw new AuthRuntimeException(new AuthError().nextError("查询失败"));
        }
        return new BaseOutDTO().setData(ret);
    }

    public BaseOutDTO selectUserByPhone( String phone) {
        UserPo ret = userService.selectByPhone(phone);
        if (ObjectUtils.isEmpty(ret)) {
            log.error("查询失败");
            throw new AuthRuntimeException(new AuthError().nextError("查询失败"));
        }
        return new BaseOutDTO().setData(ret);
    }

    public BaseOutPageDTO selectPageUser( BaseInPageDTO inPageDTO, String phone) {
        QueryUserDTO queryUserDTO = new QueryUserDTO();
        queryUserDTO.setPageNum(inPageDTO.getPageNum());
        queryUserDTO.setPageSize(inPageDTO.getPageSize());
        queryUserDTO.setPhone(phone);
        PageData pageData = userService.selectPageUser(queryUserDTO);
        return new BaseOutPageDTO().setData(pageData);
    }
}
