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
     * 用户名
     */
    private String user;

    /**
     * 二维码被查看次数
     */
    private Integer viewNum;

    private String address;

    private String name;

    private String info;
}
