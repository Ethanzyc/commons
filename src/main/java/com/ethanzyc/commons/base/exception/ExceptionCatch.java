package com.ethanzyc.commons.base.exception;

import com.ethanzyc.commons.base.response.Result;
import com.ethanzyc.commons.base.response.ResultCode;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ethan
 * @date 2019/8/27 23:44
 */
@ControllerAdvice
@Slf4j
public class ExceptionCatch {

    /**
     * 定义map，配置异常类型所对应的错误代码
     * ImmutableMap 不可更改
     */
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    /**
     * 构建map
     */
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customException(CustomException customException) {
        log.error("==========customException==========");
        return Result.fail(customException.getCode(), customException.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("==========MethodArgumentNotValidException==========");
        log.error("{}", exception.getMessage(), exception);
        return Result.fail(ResultCode.SYSTEM_ERROR.code(), exception.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception exception) {
        log.error("==========exception==========");
        log.error("exception", exception);

        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        // 从异常类型找对应的
        ResultCode code = EXCEPTIONS.get(exception.getClass());
        if (code != null) {
            return Result.fail(code);
        }
        // 找不到统一为系统异常
        ResultCode resultCode = ResultCode.SYSTEM_ERROR;
        return Result.fail(resultCode);
    }

    static {
        builder.put(NullPointerException.class, ResultCode.NULL_POINT);
    }
}
