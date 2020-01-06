package ink.baojie.cloud.base.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author renbaojie
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class BaseError implements Serializable {
    protected int code;
    protected String msg;
    public static final BaseError SYS_ERR = new BaseError(10000, "服务器异常");
    public static final BaseError ERR_REQUEST = new BaseError(400, "错误请求");

    public BaseError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
