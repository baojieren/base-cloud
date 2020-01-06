package ink.baojie.cloud.base.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author renbaojie
 */
@Data
@NoArgsConstructor
public class BaseError implements Serializable {
    private int code;
    private String msg;

    public static final int SYS_ERR_CODE_START = 10000;
    public static int COMMON_ERR_CODE_START = 2000;

    public static final BaseError ERR_REQUEST = new BaseError(400, "错误请求");
    public static final BaseError AUTH_FAILED = new BaseError(401, "鉴权失败");
    public static final BaseError LOGIN_FAILED = new BaseError(402, "登录失败");
    public static final BaseError REJECT = new BaseError(403, "用户无权限访问");
    public static final BaseError SYS_ERR = new BaseError(SYS_ERR_CODE_START, "服务器异常");

    public static final BaseError USER_NOT_EXIST = new BaseError(3, "用户不存在");
    public static final BaseError ERR_PASSWORD = new BaseError(4, "密码错误");

    public BaseError nextError(String msg) {
        COMMON_ERR_CODE_START++;
        this.code = COMMON_ERR_CODE_START;
        this.msg = msg;
        return this;
    }

    public BaseError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
