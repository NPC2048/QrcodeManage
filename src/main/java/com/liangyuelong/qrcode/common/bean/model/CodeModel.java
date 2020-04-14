package com.liangyuelong.qrcode.common.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二维码 model
 *
 * @author yuelong.liang
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CodeModel {

    private Long id;

    private UserModel user;

    private String content;

    private Integer viewNum;

    private String address;

    private Long addressId;

    /**
     * 二维码名称
     */
    private String name;

    /**
     * 二维码信息
     */
    private String info;

}
