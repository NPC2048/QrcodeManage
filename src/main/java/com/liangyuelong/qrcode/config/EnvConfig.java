package com.liangyuelong.qrcode.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 环境变量配置
 *
 * @author yuelong.liang
 */
@Configuration
@ConfigurationProperties(prefix = "env")
@Data
public class EnvConfig {

    private String baseUrl;

}
