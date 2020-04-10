package com.liangyuelong.qrcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangyuelong.qrcode.common.BizException;
import com.liangyuelong.qrcode.common.form.user.RegisterForm;
import com.liangyuelong.qrcode.dao.UserMapper;
import com.liangyuelong.qrcode.entity.User;
import com.liangyuelong.qrcode.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户 service 实现
 *
 * @author yuelong.liang
 */
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public User getByUsername(String username) {
        return this.baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public void register(RegisterForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setContent("");
        try {
            this.baseMapper.insert(user);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new BizException("注册失败, 请重试");
        }
    }
}
