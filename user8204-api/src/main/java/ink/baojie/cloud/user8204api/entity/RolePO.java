package ink.baojie.cloud.user8204api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RolePO implements Serializable {
    /**
    * 角色id
    */
    private Integer id;

    /**
    * 角色标签, 如 admin / member
    */
    private String roleTag;

    /**
    * 角色描述文本
    */
    private String roleName;

    /**
    * 0:无效 1:有效
    */
    private Integer valid;

    private String createTime;

    private String updateTime;

    private static final long serialVersionUID = 1L;
}
