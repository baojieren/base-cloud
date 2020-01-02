package ink.baojie.cloud.user8204api.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserPO implements Serializable {
    private Integer id;

    private String userName;

    private String password;

    private String phone;

    /**
    * 微信openId
    */
    private String openId;

    /**
    * 头像图片地址
    */
    private String avatar;

    private LocalDateTime birthday;

    /**
    * 性别 0:未知 1:男 2:女
    */
    private Integer gender;

    /**
    * 0:无效 1:有效
    */
    private Integer valid;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
