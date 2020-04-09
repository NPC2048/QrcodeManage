package com.liangyuelong.qrcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangyuelong.qrcode.dao.UserMapper;
import com.liangyuelong.qrcode.entity.User;
import com.liangyuelong.qrcode.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户 service 实现
 *
 * @author yuelong.liang
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
