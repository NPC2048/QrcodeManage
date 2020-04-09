package com.liangyuelong.qrcode.common.form.code;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 二维码编辑表单
 *
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CodeEditForm extends BaseCodeForm {

    @NotNull(message = "二维码 id 不能为空")
    private Long id;

}
