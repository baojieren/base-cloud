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
 * 权限表
 * </p>
 *
 * @author baojieren
 * @since 2020-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_action")
public class ActionPo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
