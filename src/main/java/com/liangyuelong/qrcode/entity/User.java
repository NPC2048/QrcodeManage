package com.liangyuelong.qrcode.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author yuelong.liang
 */
@TableName("2code_user")
@Data
public class User implements UserDetails {
    private Long id;

    private String username;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String password;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
