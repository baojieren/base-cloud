package ink.baojie.cloud.user8204api.bean.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
@Data
@Table(name = "t_action")
public class ActionPo implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 行为标签, 比如接口地址
     */
    private String actionTag;

    /**
     * 行为名称, 比如接口名称
     */
    private String actionName;

    /**
     * 0:无效 1:有效
     */
    private Integer valid;

    private String createTime;

    private String updateTime;


}
