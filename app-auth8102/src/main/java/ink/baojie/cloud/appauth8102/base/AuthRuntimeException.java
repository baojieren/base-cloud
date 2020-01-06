package ink.baojie.cloud.appauth8102.base;

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
public class AuthRuntimeException extends BaseRuntimeException {

    public AuthRuntimeException(AuthError error) {
        super(error);
    }
}
