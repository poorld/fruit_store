package me.teenyda.fruit.common.net.resp;

import java.io.Serializable;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public class BaseResponse<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
