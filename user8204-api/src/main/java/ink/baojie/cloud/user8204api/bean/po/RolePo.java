package ink.baojie.cloud.user8204api.bean.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
@Data
@Table(name = "t_role")
public class RolePo implements Serializable {

    /**
     * 角色id
     */
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
