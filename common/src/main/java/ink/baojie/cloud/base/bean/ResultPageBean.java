package ink.baojie.cloud.base.bean;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 底层服务返回给application层的分页数据实体类
 *
 * @author renbaojie
 */
public class ResultPageBean implements Serializable {
    private String requestId;
    private boolean success;
    private Integer code;
    private String msg;
    public PageData data;

    public ResultPageBean(String requestId) {
        this.requestId = requestId;
        this.success = true;

        this.data = new PageData();
        this.data.total = 0;
        this.data.list = Collections.EMPTY_LIST;
    }

    public ResultPageBean setData(int total, List list) {
        this.data.total = total;
        this.data.list = list;
        return this;
    }

    public ResultPageBean fail(BaseError baseError) {
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        this.success = false;
        return this;
    }

    public ResultPageBean fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = false;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public boolean isSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public PageData getData() {
        return data;
    }
}
