package ink.baojie.cloud.base.exception;

import lombok.*;

/**
 * 异常处理
 *
 * @author renbaojie
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BaseRuntimeException extends RuntimeException {
    public Integer code;

    public BaseRuntimeException(BaseError error) {
        super(error.getMsg());
        this.code = error.getCode();
    }
}
