package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.common.NoLogException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controller 错误处理
 *
 * @author yuelong.liang
 */
@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

    /**
     * 返回参数绑定异常
     *
     * @param e 异常对象
     * @return R
     */
    @ExceptionHandler(BindException.class)
    public BindException bindException(BindException e) {
        return e;
    }

    @ExceptionHandler(NoLogException.class)
    public NoLogException noLogException(NoLogException e) {
        return e;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return e;
    }

    /**
     * 无权限访问
     *
     * @param e e
     * @return R
     */
    @ExceptionHandler(AccessDeniedException.class)
    public AccessDeniedException accessDeniedException(AccessDeniedException e) {
        log.info(ExceptionUtils.getStackTrace(e));
        return e;
    }

    /**
     * 统一处理异常
     *
     * @param e 异常对象
     * @return R
     */
    @ExceptionHandler(Exception.class)
    public Exception exception(Exception e) {
        log.info(ExceptionUtils.getStackTrace(e));
        return e;
    }

}
