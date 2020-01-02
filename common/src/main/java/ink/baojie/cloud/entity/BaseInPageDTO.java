package ink.baojie.cloud.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseInPageDTO implements Serializable {
    public Integer pageIndex = 1;
    public Integer pageSize = 15;
}
