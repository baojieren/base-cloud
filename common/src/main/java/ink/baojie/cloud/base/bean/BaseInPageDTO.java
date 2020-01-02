package ink.baojie.cloud.base.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseInPageDTO implements Serializable {
    public Integer pageIndex = 1;
    public Integer pageSize = 10;
}
