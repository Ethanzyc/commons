package com.ethanzyc.commons.base.exception;

import com.ethanzyc.commons.base.response.ResultCode;

/**
 * @author ethan
 * @date 2019/6/24 23:19
 */
public class CustomException extends RuntimeException {

    /**
     * 自定义 rest 请求 code
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    public CustomException(String msg) {
        this.code = ResultCode.SYSTEM_ERROR.code();
        this.msg = msg;
    }


    public CustomException(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
