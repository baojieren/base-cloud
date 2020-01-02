package ink.baojie.cloud.base.bean;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author renbaojie
 */
public class BaseOutDTO implements Serializable {
    private String requestId;
    private Integer code;
    private String msg;
    private Object data;

    public BaseOutDTO(String requestId) {
        this.requestId = requestId;
        this.code = 200;
        this.msg = "ok";
    }

    public BaseOutDTO fail(BaseError baseError) {
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        return this;
    }

    public BaseOutDTO fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public BaseOutDTO setData(Object data) {
        this.data = data;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
