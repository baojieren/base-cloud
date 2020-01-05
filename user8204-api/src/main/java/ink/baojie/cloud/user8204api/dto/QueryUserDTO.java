package ink.baojie.cloud.user8204api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryUserDTO implements Serializable {
    public Integer pageNum = 1;
    public Integer pageSize = 10;
    public String phone;
}
