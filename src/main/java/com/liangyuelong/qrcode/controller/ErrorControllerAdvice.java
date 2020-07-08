package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.common.NoLogException;
import com.liangyuelong.qrcode.common.bean.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

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
    @ExceptionHandler
    public R bindException(BindException e) {
        return R.failed(e.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler
    public R noLogException(NoLogException e) {
        return R.failed(e.getMessage());
    }

    @ExceptionHandler
    public R httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return R.failed(e.getMessage());
    }

    /**
     * 无权限访问
     *
     * @param e e
     * @return R
     */
    @ExceptionHandler
    public R accessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.warn(SecurityContextHolder.getContext().getAuthentication() + ":" + request.getRequestURI() + "?" + request.getQueryString() + ":" + e.getMessage());
        return R.failed(e.getMessage());

    }

    /**
     * 统一处理异常
     *
     * @param e 异常对象
     * @return R
     */
    @ExceptionHandler
    public R exception(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return R.failed("服务器错误");
    }


}
