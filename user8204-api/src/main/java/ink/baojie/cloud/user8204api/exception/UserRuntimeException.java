package ink.baojie.cloud.user8204api.exception;

import ink.baojie.cloud.base.exception.BaseError;
import ink.baojie.cloud.base.exception.BaseRuntimeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 异常处理
 *
 * @author renbaojie
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserRuntimeException extends BaseRuntimeException {

    public UserRuntimeException(BaseError error) {
        super(error);
    }
}
