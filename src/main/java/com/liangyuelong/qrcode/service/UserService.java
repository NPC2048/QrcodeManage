package com.liangyuelong.qrcode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liangyuelong.qrcode.common.form.user.RegisterForm;
import com.liangyuelong.qrcode.entity.User;

/**
 * 用户 service 接口
 *
 * @author yuelong.liang
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

    /**
     * 注册用户, 不抛出异常代表注册成功
     *
     * @param form form
     */
    void register(RegisterForm form);

}
