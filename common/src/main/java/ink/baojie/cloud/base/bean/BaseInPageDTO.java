package ink.baojie.cloud.base.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 前端分页查询入参实体的父类
 *
 * @author renbaojie
 */
@Data
public class BaseInPageDTO implements Serializable {
    public Integer pageNum = 1;
    public Integer pageSize = 10;
}
