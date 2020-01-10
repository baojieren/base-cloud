package ink.baojie.cloud.base.bean;

import ink.baojie.cloud.base.exception.BaseError;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页查询rest api返回结果实体类
 *
 * @author baojieren
 */
public class BaseOutPageDTO implements Serializable {
    private String requestId;
    private Integer code;
    private String msg;
    private PageData data;

    public BaseOutPageDTO(String requestId) {
        this.requestId = requestId;
        this.code = 200;
        this.msg = "ok";

        this.data = new PageData();
        this.data.total = 0;
        this.data.list = Collections.EMPTY_LIST;
    }

    public BaseOutPageDTO setData(int total, List list) {
        this.data.total = total;
        this.data.list = list;
        return this;
    }

    public BaseOutPageDTO setData(PageData pageData) {
        this.data = pageData;
        return this;
    }

    public BaseOutPageDTO fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public BaseOutPageDTO fail(BaseError baseError) {
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        return this;
    }

    public String getRequestId() {
        return requestId;
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
