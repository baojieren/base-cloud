package ink.baojie.cloud.base.bean;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.Getter;

import java.io.Serializable;

/**
 * rest api返回给前端的结果实体类
 *
 * @author renbaojie
 */
@Getter
public class BaseOutDTO implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public BaseOutDTO() {
        this.code = 200;
        this.msg = "ok";
    }

    public BaseOutDTO setData(Object data) {
        this.data = data;
        return this;
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
}
