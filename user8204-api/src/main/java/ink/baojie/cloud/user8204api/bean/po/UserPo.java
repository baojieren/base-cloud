package ink.baojie.cloud.user8204api.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class UserPo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
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


}
