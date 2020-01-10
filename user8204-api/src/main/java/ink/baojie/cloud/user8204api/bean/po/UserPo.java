package ink.baojie.cloud.user8204api.bean.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
@Data
@Table(name = "t_user")
public class UserPo implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
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

    private String birthday;

    /**
     * 性别 0:未知 1:男 2:女
     */
    private Integer gender;

    /**
     * 0:无效 1:有效
     */
    private Integer valid;

    private String createTime;

    private String updateTime;


}
