package com.liangyuelong.qrcode.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liangyuelong.qrcode.aop.annotation.Log;
import com.liangyuelong.qrcode.common.NoLogException;
import com.liangyuelong.qrcode.common.bean.R;
import com.liangyuelong.qrcode.common.form.user.LoginForm;
import com.liangyuelong.qrcode.common.form.user.RegisterForm;
import com.liangyuelong.qrcode.entity.User;
import com.liangyuelong.qrcode.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 登錄處理 controller
 *
 * @author yuelong.liang
 */
@RestController
@Slf4j
@Log
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public R register(@Valid RegisterForm form) {
        return R.SUCCESS;
    }

    @RequestMapping("/login")
    public R login(@Valid LoginForm form) {
        User user = userService.getOne(new QueryWrapper<User>().eq("user", form.getUsername()));
        if (user == null) {
            throw new NoLogException("该用户不存在");
        }
        // 验证密码
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw new NoLogException("密码错误");
        }
        SecurityContextHolder.getContext().getAuthentication();
        return R.SUCCESS;
    }

}
