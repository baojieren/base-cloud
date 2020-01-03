package ink.baojie.cloud.user8204api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ActionPO implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
