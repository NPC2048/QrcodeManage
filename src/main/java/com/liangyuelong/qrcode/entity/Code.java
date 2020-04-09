package com.liangyuelong.qrcode.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("2code_code")
@Data
public class Code {

    /**
     * id
     */
    private Long id;

    /**
     * 关联的用户id
     */
    private Long userId;

    /**
     * 二维码内容
     */
    private String content;

    /**
     * 二维码被查看次数
     */
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
