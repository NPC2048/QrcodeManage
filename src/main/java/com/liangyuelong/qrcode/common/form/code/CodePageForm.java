package com.liangyuelong.qrcode.common.form.code;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liangyuelong.qrcode.entity.Code;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 博客分页查询表单
 *
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CodePageForm extends Page<Code> {

    /**
     * 二维码名称
     */
    private String name;

    /**
     * 用户 id
     */
    @NotNull(message = "用户 id 不能为空")
    private Long userId;

    /**
     * 二维码内容
     */
    private String content;

    /**
     * 地址
     */
    private String address;

    /**
     * 地址 id
     */
    private Long addressId;


}
