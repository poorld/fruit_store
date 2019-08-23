package me.teenyda.mvp_template.common.net.resp;

import java.io.Serializable;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public class BaseResponse implements Serializable {

    private int code;

    private String msg;

    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
