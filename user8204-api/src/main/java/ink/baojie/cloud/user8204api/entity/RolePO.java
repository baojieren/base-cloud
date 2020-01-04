package ink.baojie.cloud.user8204api.entity;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_role")
public class RolePO implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
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

}
