package com.liangyuelong.qrcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangyuelong.qrcode.dao.CodeMapper;
import com.liangyuelong.qrcode.entity.Code;
import com.liangyuelong.qrcode.service.CodeService;
import org.springframework.stereotype.Service;

/**
 * 二维码 service 实现
 *
 * @author yuelong.liang
 */
@Service("codeService")
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CodeService {
}
