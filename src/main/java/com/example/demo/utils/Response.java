package com.example.demo.utils;

import com.example.demo.model.BaseResp;

public class Response {


    public static BaseResp ok(Object data) {


        return new BaseResp(data);
    }

    public static BaseResp err(String msg, int code) {

        return new BaseResp(code, msg, null);
    }
}
