package com.liangyuelong.qrcode.common.form.code;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author yuelong.liang
 */
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
    @Length(max = 32, message = "二维码名称最多为 32 个字符")
    protected String name;

    /**
     * 二维码内容
     */
    @NotBlank(message = "二维码内容不能为空")
    @Length(max = 32, message = "对不起，内容过长, 最多只能 512 个字符")
    protected String content;

    /**
     * 地区 id
     */
    @NotBlank(message = "地区不能为空")
    @Length(max = 64, message = "对不起, 地区 id 过长")
    @Pattern(regexp = "\\d+(,\\d+){0,2}", message = "地区格式错误")
    protected String addressId;

    /**
     * 备注信息
     */
    @Length(max = 16, message = "备注过长, 最多只能为 16 个字符")
    protected String info;


}
