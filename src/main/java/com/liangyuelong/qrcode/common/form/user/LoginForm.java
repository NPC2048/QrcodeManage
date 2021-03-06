package com.liangyuelong.qrcode.common.form.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录提交表单
 *
 * @author yuelong.liang
 */
@Data
public class LoginForm {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    private String verifyCode;

}
