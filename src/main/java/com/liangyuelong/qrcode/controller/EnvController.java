package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.config.EnvConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 变量获取 controller
 *
 * @author yuelong.liang
 */
@RestController
@RequestMapping("/env")
public class EnvController implements InitializingBean {

    @Resource
    public EnvConfig envConfig;

    @GetMapping("/baseUrl")
    public String baseUrl() {
        return envConfig.getBaseUrl();
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(envConfig.getBaseUrl());
    }
}
