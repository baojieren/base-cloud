package ink.baojie.cloud.base.dto;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author renbaojie
 */
@Data
@Accessors(chain = true)
public class BaseOutDTO implements Serializable {
    private Integer code;
    private String msg;
    private String requestId;
    private Object data;

    public BaseOutDTO(String requestId) {
        this.requestId = requestId;
        this.code = 200;
        this.msg = "ok";
    }

    public BaseOutDTO fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public BaseOutDTO fail(BaseError baseError) {
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        return this;
    }
}
