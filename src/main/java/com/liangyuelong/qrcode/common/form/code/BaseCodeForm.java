package com.liangyuelong.qrcode.common.form.code;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public abstract class BaseCodeForm {

    /**
     * 用户 id
     */
    @NotNull(message = "用户 id 不能为空")
    protected Long userId;

    /**
     * 二维码名称
     */
    @NotBlank(message = "二维码名称不能为空")
    protected String name;

    /**
     * 二维码内容
     */
    @NotBlank(message = "二维码内容不能为空")
    protected String content;

    protected String address;

    protected Long addressId;

    protected String info;


}
