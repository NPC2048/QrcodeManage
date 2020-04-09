package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.common.BizException;
import com.liangyuelong.qrcode.common.NoLogException;
import com.liangyuelong.qrcode.common.bean.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.BindException;
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
