package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.common.NoLogException;
import com.liangyuelong.qrcode.common.constant.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * controller 错误处理
 *
 * @author yuelong.liang
 */
@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

    private View jsonView = new MappingJackson2JsonView();

    /**
     * 返回参数绑定异常
     *
     * @param e 异常对象
     * @return R
     */
    @ExceptionHandler
    public ModelAndView bindException(BindException e) {
        return result(e.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler
    public ModelAndView noLogException(NoLogException e) {
        return result(e.getMessage());
    }

    @ExceptionHandler
    public ModelAndView httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return result(e.getMessage());
    }

    /**
     * 无权限访问
     *
     * @param e e
     * @return R
     */
    @ExceptionHandler
    public ModelAndView accessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.warn(SecurityContextHolder.getContext().getAuthentication() + ":" + request.getRequestURI() + "?" + request.getQueryString() + ":" + e.getMessage());
        return result(e.getMessage());
    }

    /**
     * 统一处理异常
     *
     * @param e 异常对象
     * @return R
     */
    @ExceptionHandler
    public ModelAndView exception(Exception e) {
        log.info(ExceptionUtils.getStackTrace(e));
        return result("服务器错误");
    }

    private ModelAndView result(String msg) {
        return new ModelAndView(jsonView, new ModelMap("state", GlobalConstant.FAILED).addAttribute("msg", msg));
    }

}
