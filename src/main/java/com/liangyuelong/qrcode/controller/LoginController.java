package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.aop.annotation.Log;
import com.liangyuelong.qrcode.common.NoLogException;
import com.liangyuelong.qrcode.common.bean.R;
import com.liangyuelong.qrcode.common.constant.GlobalConstant;
import com.liangyuelong.qrcode.common.form.user.LoginForm;
import com.liangyuelong.qrcode.common.form.user.RegisterForm;
import com.liangyuelong.qrcode.entity.User;
import com.liangyuelong.qrcode.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 登錄處理 controller
 *
 * @author yuelong.liang
 */
@RestController
@Log
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 用户注册
     *
     * @param form 表单
     * @return R
     */
    @PostMapping("/register")
    public R register(@Valid RegisterForm form) {
        // 查询用户是否存在
        User user = userService.getByUsername(form.getUsername());
        if (user != null) {
            throw new NoLogException("该用户名已存在");
        }
        userService.register(form);
        return R.SUCCESS;
    }

    @PostMapping("/login")
    public R login(HttpSession session, @Valid LoginForm form) {
        User user = userService.getByUsername(form.getUsername());
        if (user == null) {
            throw new NoLogException("该用户不存在");
        }
        // 验证密码
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw new NoLogException("密码错误");
        }
        // 登录成功, 放入容器
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
        token.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(token);
        // 往 session 中存储用户 id, 便于验证
        session.setAttribute(GlobalConstant.USER_ID, user.getId());
        return R.SUCCESS;
    }

}
