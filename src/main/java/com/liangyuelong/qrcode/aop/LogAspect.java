package com.liangyuelong.qrcode.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 日志切面, 在方法执行前后打印日志与返回值
 *
 * @author yuelong.liang
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * 对 @log 注解的方法进行环绕切面
     *
     * @param joinPoint jp
     * @return Object
     * @throws Throwable e
     */
    @Around("@annotation(com.liangyuelong.qrcode.aop.annotation.Log) || @within(com.liangyuelong.qrcode.aop.annotation.Log)")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object runInfo = joinPoint.getTarget().getClass().getSimpleName() + "." + methodSignature.getName();
        log.info("=============== " + runInfo + " Begin, args: " + Arrays.toString(joinPoint.getArgs()) + "===============");
        try {
            Object result = joinPoint.proceed();
            log.info("=============== " + runInfo + " End, result:" + result + " ===============");
            return result;
        } catch (Throwable throwable) {
            log.error("=============== " + runInfo + " Error ===============");
            // 打印错误后交给全局异常处理器处理
            throw throwable;
        }
    }
}
