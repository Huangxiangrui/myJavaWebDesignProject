package com.hxr.shopping.utils;

import lombok.Data;

@Data
public class R {

    private String code;
    private String message;
    private Object data;

    public R() {
    }

    public R(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


}