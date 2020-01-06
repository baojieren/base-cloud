package ink.baojie.cloud.user8204api.exception;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.NoArgsConstructor;

/**
 * @author renbaojie
 */
@NoArgsConstructor
public class UserError extends BaseError {

    private static int COMMON_ERR_CODE_START = 10000;
    public UserError(int code, String msg) {
        super(code, msg);
    }

    public UserError nextError(String msg) {
        COMMON_ERR_CODE_START++;
        this.code = COMMON_ERR_CODE_START;
        this.msg = msg;
        return this;
    }
}
