package ink.baojie.cloud.base.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseInPageDTO implements Serializable {
    public String requestId;
    public Integer pageIndex = 1;
    public Integer pageSize = 10;
}
