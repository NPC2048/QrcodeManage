package com.liangyuelong.qrcode.aop.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author yuelong.liang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Log {
}
