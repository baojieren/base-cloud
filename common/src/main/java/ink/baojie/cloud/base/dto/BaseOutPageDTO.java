package ink.baojie.cloud.base.dto;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author baojieren
 */
@Data
public class BaseOutPageDTO implements Serializable {
    public Integer code;
    public String msg;
    public String requestId;
    public Data data;

    public BaseOutPageDTO(String requestId) {
        this.code = 200;
        this.msg = "ok";
        this.requestId = requestId;
        this.data.total = 0;
        this.data.list = Collections.EMPTY_LIST;
    }

    public BaseOutPageDTO setData(String requestId, Integer total, List list) {
        this.code = 200;
        this.msg = "ok";
        this.requestId = requestId;

        this.data.total = total;
        this.data.list = list;
        return this;
    }

    public BaseOutPageDTO fail(String requestId, int code, String msg) {
        this.requestId = requestId;
        this.code = code;
        this.msg = msg;
        return this;
    }

    public BaseOutPageDTO fail(String requestId, BaseError baseError) {
        this.requestId = requestId;
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        return this;
    }

    @lombok.Data
    public class Data {
        public int total;
        public List list;
    }
}
