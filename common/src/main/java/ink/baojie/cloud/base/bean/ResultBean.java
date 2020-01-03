package ink.baojie.cloud.base.bean;

import ink.baojie.cloud.base.exception.BaseError;

import java.io.Serializable;

/**
 * 底层服务返回给application层的数据实体类
 *
 * @author renbaojie
 */
public class ResultBean<T> implements Serializable {
    private String requestId;
    private boolean success;
    private Integer code;
    private String msg;
    private T data;

    public ResultBean(String requestId) {
        this.requestId = requestId;
        this.code=200;
        this.msg="OK";
        this.success = true;
    }

    public ResultBean fail(BaseError baseError) {
        this.success = false;
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        return this;
    }

    public ResultBean fail(int code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResultBean<T> setData(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
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

}
