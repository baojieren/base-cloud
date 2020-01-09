package ink.baojie.cloud.appauth8102.bean.dto;

import ink.baojie.cloud.base.bean.BaseInDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginDTO extends BaseInDTO {
    private String phone;
    private String password;
    private String openId;
}
