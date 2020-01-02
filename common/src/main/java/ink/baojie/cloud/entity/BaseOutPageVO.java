package ink.baojie.cloud.entity;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baojieren
 */
@Data
public class BaseOutPageVO implements Serializable {
    private int code;
    private String msg;
    public Data data;

    public BaseOutPageVO() {
        this.code = 200;
        this.msg = "ok";
        Data data = new Data();
        data.total = 0;
        data.list = new ArrayList();
        this.data = data;
    }

    public BaseOutPageVO setData(Integer total, List list) {
        this.getData().setTotal(total);
        this.getData().setList(list);
        return this;
    }

    public BaseOutPageVO fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public BaseOutPageVO fail(BaseError baseError) {
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        return this;
    }

    @lombok.Data
    public class Data {
        public Integer total;
        public List list;
    }
}
