package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.common.bean.R;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 统一异常与返回值处理
 *
 * @author yuelong.liang
 */
@ControllerAdvice
public class ResultAndErrorHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof BindException) {
            return R.failed(((BindException) o).getAllErrors().get(0).getDefaultMessage());
        } else if (o instanceof Throwable) {
            return R.result((Exception) o);
        }
        return R.result(o);
    }
}
