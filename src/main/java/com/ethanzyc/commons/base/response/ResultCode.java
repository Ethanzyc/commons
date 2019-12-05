package com.ethanzyc.commons.base.response;

/**
 *
 * 返回值code
 * @author ethan
 * @date 2019/8/27 22:28
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(500, "系统异常"),

    /**
     * 空指针
     */
    NULL_POINT(510, "空指针异常"),

    PHONE_ERROR("手机号格式错误"),
    FREQUENTLY_SEND("发送验证码较为频繁，请稍后重试"),
    SMS_FAIL("系统异常，验证码发送失败"),
    PHONE_HAS_REGISTER("该手机号已注册"),
    VERITY_CODE_TIME_OUT("验证码已过期"),
    VERITY_CODE_ERROR("验证码错误"),
    SEND_SMS_TIMES_TOO_MUCH("今日发送验证码已超限制"),
    /**
     * jobDetail不存在
     */
    TASK_NO_JOB_DETAIL(550, "jobDetail不存在"),
    /**
     * 任务不存在或已被删除
     */
    TASK_NOT_EXIST(550, "任务不存在或已被删除");

    /**
     * 自定义 rest 请求 code
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResultCode(String message) {
        this.code = 500;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
