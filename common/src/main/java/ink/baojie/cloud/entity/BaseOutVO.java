package ink.baojie.cloud.entity;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.Data;

import java.io.Serializable;

/**
 * @author renbaojie
 */
@Data
public class BaseOutVO implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public BaseOutVO() {
        this.code = 200;
        this.msg = "ok";
    }

    public BaseOutVO(String msg) {
        this.msg = msg;
    }

    public BaseOutVO fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public BaseOutVO fail(BaseError baseError) {
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        return this;
    }
}
