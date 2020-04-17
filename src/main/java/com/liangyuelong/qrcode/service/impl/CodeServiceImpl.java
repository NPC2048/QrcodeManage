package com.liangyuelong.qrcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.liangyuelong.qrcode.common.BizException;
import com.liangyuelong.qrcode.common.form.code.CodeAddForm;
import com.liangyuelong.qrcode.common.form.code.CodeEditForm;
import com.liangyuelong.qrcode.dao.CodeMapper;
import com.liangyuelong.qrcode.entity.Code;
import com.liangyuelong.qrcode.service.CodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 二维码 service 实现
 *
 * @author yuelong.liang
 */
@Service("codeService")
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CodeService {

    @Override
    public boolean save(CodeAddForm form) {
        Code code = new Code();
        code.setViewNum(0);
        BeanUtils.copyProperties(form, code);
        // 存储
        return this.save(code);
    }

    @Override
    public boolean update(CodeEditForm form) {
        Code code = this.baseMapper.selectById(form.getId());
        if (code == null) {
            throw new BizException("该二维码不存在");
        }
        BeanUtils.copyProperties(form, code);
        return SqlHelper.retBool(this.baseMapper.updateById(code));
    }

    @Override
    public List<Code> listByUsername(String username) {
        return this.baseMapper.listByUsername(username);
    }

}
