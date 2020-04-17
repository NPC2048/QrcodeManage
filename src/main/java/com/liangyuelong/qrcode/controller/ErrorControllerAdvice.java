package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.common.BizException;
import com.liangyuelong.qrcode.common.NoLogException;
import com.liangyuelong.qrcode.common.bean.R;
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
    public R bindException(BindException e) {
        return R.failed(e.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(NoLogException.class)
    public R noLogException(NoLogException e) {
        return R.failed(e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public R bizException(BizException e) {
        log.info(ExceptionUtils.getStackTrace(e));
        return R.failed(e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return R.failed("请求方法不支持:" + e.getMethod());
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
//        return R.failed(e.getMessage());
        return e;
    }

    /**
     * 统一处理异常
     *
     * @param e 异常对象
     * @return R
     */
    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        log.info(ExceptionUtils.getStackTrace(e));
        return R.failed("服务器错误");
    }

}
