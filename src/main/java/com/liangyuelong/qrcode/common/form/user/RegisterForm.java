package com.liangyuelong.qrcode.common.form.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 注册表单
 *
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterForm extends LoginForm {

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    @Length(min = 6, max = 15, message = "密码长度不能小于 6 位且不能大于 15 位")
    private String confirmPassword;

    /**
     * 邮箱
     */
//    @NotBlank(message = "邮箱不能为空")
    private String email;

}
