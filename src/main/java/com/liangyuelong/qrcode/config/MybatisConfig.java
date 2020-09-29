package com.liangyuelong.qrcode.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Date;

/**
 * mybatis 配置
 *
 * @author yuelong.liang
 */
@Configuration
public class MybatisConfig implements MetaObjectHandler {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName("createTime", now, metaObject);
        // 密码
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
