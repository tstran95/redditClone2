package com.vn.redditclone.bean.response;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {
    SUCCESS("00" , "SUCCESS"),
    SIGNUP_FAIL("02" , "username or password wrong!"),
    INVALID_ERROR("99" , "System error....");

    private String code;


    private String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
