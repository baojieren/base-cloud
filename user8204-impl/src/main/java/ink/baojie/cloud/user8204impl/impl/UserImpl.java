package ink.baojie.cloud.user8204impl.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ink.baojie.cloud.base.bean.PageData;
import ink.baojie.cloud.user8204api.bean.dto.QueryUserDTO;
import ink.baojie.cloud.user8204api.bean.po.ActionPo;
import ink.baojie.cloud.user8204api.bean.po.RolePo;
import ink.baojie.cloud.user8204api.bean.po.UserPo;
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
import java.util.ArrayList;
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
    public Integer insertUser(String requestId, UserPo userPo) {
        int ret = userDao.insertSelective(userPo);
        if (ObjectUtils.isEmpty(ret) || ret == 0) {
            log.error("保存失败");
            throw new UserRuntimeException(UserError.DB_ERR);
        } else {
            return userPo.getId();
        }
    }

    @Override
    public Integer deleteById(String requestId, Integer userId) {
        UserPo userPo = userDao.selectByPrimaryKey(userId);
        if (ObjectUtils.isEmpty(userPo)) {
            log.warn("用户:{} 不存在", userId);
            throw new UserRuntimeException(new UserError().nextError("用户不存在"));
        }
        int ret = userDao.deleteByPrimaryKey(userId);
        if (ret <= 0) {
            log.error("删除失败", userId);
            throw new UserRuntimeException(UserError.DB_ERR);
        }
        return ret;
    }

    @Override
    public Integer updateUser(String requestId, UserPo userPo) {
        int ret = userDao.updateByPrimaryKeySelective(userPo);
        if (ObjectUtils.isEmpty(ret) || ret == 0) {
            log.error("更新失败:{}", JSONObject.toJSONString(userPo));
            throw new UserRuntimeException(UserError.DB_ERR);
        }
        return ret;
    }

    @Override
    public UserPo selectById(String requestId, Integer userId) {
        UserPo userPo = userDao.selectByPrimaryKey(userId);
        if (ObjectUtils.isEmpty(userPo)) {
            log.warn("用户:{} 不存在", userId);
            throw new UserRuntimeException(new UserError().nextError("用户不存在"));
        }
        return userPo;
    }

    @Override
    public UserPo selectByPhone(String requestId, String phone) {
        UserPo userPo = userDao.selectOneByPhone(phone);
        if (ObjectUtils.isEmpty(userPo)) {
            log.warn("手机用户:{} 不存在", phone);
            throw new UserRuntimeException(new UserError().nextError("用户不存在"));
        }
        return userPo;
    }

    @Override
    public List<RolePo> selectAllRoleByUserId(String requestId, Integer userId) {
        return roleDao.selectAllRoleByUserId(userId);
    }

    @Override
    public List<ActionPo> selectAllActionByUserId(String requestId, Integer userId) {
        return actionDao.selectAllActionByUserId(userId);
    }

    @Override
    public PageData selectPageUser(String requestId, QueryUserDTO queryUserDTO) {
        PageHelper.startPage(queryUserDTO.getPageNum(), queryUserDTO.getPageSize());
        List<UserPo> userPoS = userDao.selectUserByPage(queryUserDTO.getPhone());

        PageInfo pageInfo = new PageInfo<>(userPoS);
        PageData pageData = new PageData();
        pageData.setTotal((int) pageInfo.getTotal());
        pageData.setList(userPoS);
        return pageData;
    }
}
