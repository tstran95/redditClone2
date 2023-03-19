package com.vn.redditclone.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vn.redditclone.constant.Constant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private String code;
    private String message;
    private Object data;

    public static BaseResponse of(String code , String message , Object data) {
        return BaseResponse.builder().code(code).message(message).data(data ).build();
    }

    public static BaseResponse of(ResponseCodeEnum codeEnum) {
        return BaseResponse.builder().code(codeEnum.getCode()).message(codeEnum.getMessage()).build();
    }

    public static BaseResponse of(Object object) {
        return BaseResponse.builder().code(Constant.SUCCESS_CODE).message(Constant.SUCCESS).data(object).build();
    }
}
