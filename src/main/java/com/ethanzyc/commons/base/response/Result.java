package com.ethanzyc.commons.base.response;

/**
 * 利用builder模式封装统一返回值
 * @author ethan
 * @date 2019/8/27 20:32
 */
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    static <T> ResultBuilder<T> builder() {
        return new ResultBuilder<>();
    }

    public static <T> Result<T> success() {
        ResultBuilder<T> builder = builder();
        return builder
                .code(ResultCode.SUCCESS.code())
                .message(ResultCode.SUCCESS.message())
                .build();
    }

    public static <T> Result<T> success(T data) {
        ResultBuilder<T> builder = builder();
        return builder
                .code(ResultCode.SUCCESS.code())
                .message(ResultCode.SUCCESS.message())
                .data(data)
                .build();
    }

    public static <T> Result<T> fail() {
        ResultBuilder<T> builder = builder();
        return builder
                .code(ResultCode.SYSTEM_ERROR.code())
                .message(ResultCode.SYSTEM_ERROR.message())
                .build();
    }

    public static <T> Result<T> fail(int code, String message) {
        ResultBuilder<T> builder = builder();
        return builder
                .code(code)
                .message(message)
                .build();
    }

    public static <T> Result<T> fail(String message) {
        ResultBuilder<T> builder = builder();
        return builder
                .code(ResultCode.SYSTEM_ERROR.code())
                .message(message)
                .build();
    }

    /**
     * 错误推荐使用这个重载方法
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        ResultBuilder<T> builder = builder();
        return builder
                .code(resultCode.code())
                .message(resultCode.message())
                .build();
    }

    public static class ResultBuilder<T> {

        private int code;

        private String message;

        private T data;

        ResultBuilder() {
        }

        public ResultBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public ResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Result<T> build() {
            return new Result<>(code, message, data);
        }
    }
}
