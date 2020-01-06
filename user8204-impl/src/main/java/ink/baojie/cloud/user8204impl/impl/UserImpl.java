package ink.baojie.cloud.user8204impl.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ink.baojie.cloud.base.bean.ResultBean;
import ink.baojie.cloud.user8204api.dto.QueryUserDTO;
import ink.baojie.cloud.user8204api.entity.ActionPO;
import ink.baojie.cloud.user8204api.entity.RolePO;
import ink.baojie.cloud.user8204api.entity.UserPO;
import ink.baojie.cloud.user8204api.exception.UserError;
import ink.baojie.cloud.user8204api.exception.UserRuntimeException;
import ink.baojie.cloud.user8204api.service.UserService;
import ink.baojie.cloud.user8204impl.dao.ActionDao;
import ink.baojie.cloud.user8204impl.dao.RoleDao;
import ink.baojie.cloud.user8204impl.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author renbaojie
 */
@Slf4j
@Service
public class UserImpl implements UserService {
    @Resource
    UserDao userDao;
    @Resource
    RoleDao roleDao;
    @Resource
    ActionDao actionDao;

    @Override
    public ResultBean<Integer> insertUser(String requestId, UserPO userPO) {
        ResultBean<Integer> resultBean = new ResultBean<>(requestId);
        int ret = userDao.insertSelective(userPO);
        if (ObjectUtils.isEmpty(ret) || ret == 0) {
            log.error("保存失败");
            return resultBean.fail(UserError.DB_ERR);
        } else {
            return resultBean.setData(userPO.getId());
        }
    }

    @Override
    public ResultBean deleteById(String requestId, Integer userId) {
        ResultBean<Object> resultBean = new ResultBean<>(requestId);
        int ret = userDao.deleteByPrimaryKey(userId);
        if (ret <= 0) {
            log.error("用户:{} 不存在", userId);
            return resultBean.fail(new UserError().nextError("操作失败"));
        } else {
            return resultBean;
        }
    }

    @Override
    public ResultBean updateUser(String requestId, UserPO userPO) {
        ResultBean<Integer> resultBean = new ResultBean<>(requestId);
        int ret = userDao.updateByPrimaryKeySelective(userPO);
        int a = 1/0;
        if (ObjectUtils.isEmpty(ret) || ret == 0) {
            return resultBean.fail(UserError.DB_ERR);
        } else {
            log.info("更新成功:{}", userPO.getId());
            return resultBean;
        }
    }

    @Override
    public ResultBean<UserPO> selectById(String requestId, Integer userId) {
        UserPO userPO = userDao.selectByPrimaryKey(userId);
        return new ResultBean<UserPO>(requestId).setData(userPO);
    }

    @Override
    public ResultBean<UserPO> selectByPhone(String requestId, String phone) {
        UserPO userPO = userDao.selectOneByPhone(phone);
        return new ResultBean<UserPO>(requestId).setData(userPO);
    }

    @Override
    public ResultBean<List<RolePO>> selectAllRoleByUserId(String requestId, Integer userId) {
        List<RolePO> rolePOS = roleDao.selectAllRoleByUserId(userId);
        return new ResultBean<List<RolePO>>(requestId).setData(rolePOS);
    }

    @Override
    public ResultBean<List<ActionPO>> selectAllActionByUserId(String requestId, Integer userId) {
        List<ActionPO> actionPOS = actionDao.selectAllActionByUserId(userId);
        return new ResultBean<List<ActionPO>>(requestId).setData(actionPOS);
    }

    @Override
    public ResultBean<List<UserPO>> selectPageUser(String requestId, QueryUserDTO queryUserDTO) {
        PageHelper.startPage(queryUserDTO.getPageNum(), queryUserDTO.getPageSize());
        List<UserPO> userPOS = userDao.selectPageUser(queryUserDTO.getPhone());
        log.info(JSONObject.toJSONString(userPOS));

        PageInfo pageInfo = new PageInfo<>(userPOS);

        // long total = ((Page) userPOS).getTotal();
        log.info("中数:{}", pageInfo.getTotal());
        return new ResultBean<>(requestId);
    }
}
