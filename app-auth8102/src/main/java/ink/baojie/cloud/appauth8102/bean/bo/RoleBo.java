package ink.baojie.cloud.appauth8102.bean.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleBo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色标签, 如"ADMIN","USER"等
     */
    private String roleTag;

    /**
     * 角色名
     */
    private String roleName;

}
