package ink.baojie.cloud.appauth8102.base;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.NoArgsConstructor;

/**
 * @author renbaojie
 */
@NoArgsConstructor
public class AuthError extends BaseError {
    public static final AuthError USER_NOT_EXIST = new AuthError(3, "用户不存在");
    public static final AuthError ERR_PASSWORD = new AuthError(4, "密码错误");
    public static final AuthError AUTH_FAILED = new AuthError(401, "鉴权失败");
    public static final AuthError LOGIN_FAILED = new AuthError(402, "登录失败");
    public static final AuthError REJECT = new AuthError(403, "用户无权限访问");

    private static int COMMON_ERR_CODE_START = 10000;
    public AuthError(int code, String msg) {
        super(code, msg);
    }

    public AuthError nextError(String msg) {
        COMMON_ERR_CODE_START++;
        this.code = COMMON_ERR_CODE_START;
        this.msg = msg;
        return this;
    }
}
