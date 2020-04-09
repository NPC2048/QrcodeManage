package com.liangyuelong.qrcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableConfigurationProperties
@EnableAspectJAutoProxy
@SpringBootApplication
@MapperScan("com.liangyuelong.qrcode.dao")
public class QrcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrcodeApplication.class, args);
    }

}
