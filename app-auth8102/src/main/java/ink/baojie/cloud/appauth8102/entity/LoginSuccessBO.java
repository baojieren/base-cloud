package ink.baojie.cloud.appauth8102.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LoginSuccessBO implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像图片地址
     */
    private String avatar;

    /**
     * token
     */
    private String token;

    /**
     * 角色列表
     */
    List<RoleBO> roleList;

}
