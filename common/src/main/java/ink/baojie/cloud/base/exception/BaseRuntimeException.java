package ink.baojie.cloud.base.exception;

import lombok.*;

/**
 * 异常处理
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRuntimeException extends RuntimeException {
    private BaseError error;
    protected Throwable throwable;

    public BaseRuntimeException(BaseError error) {
        this.error = error;
    }
}
