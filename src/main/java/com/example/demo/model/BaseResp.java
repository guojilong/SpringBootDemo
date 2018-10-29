package com.example.demo.model;

public class BaseResp<T> {
    private int code = 200;
    private String msg = "";
    private Object data;

    public BaseResp(Object data) {
        this.data = data;
    }

    public BaseResp(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
