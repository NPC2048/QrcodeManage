package com.liangyuelong.qrcode.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author yuelong.liang
 */
@TableName("2code_user")
@Data
public class User {
    private Long id;

    private String user;

    private String password;

    private String content;

    private Date time;

}
