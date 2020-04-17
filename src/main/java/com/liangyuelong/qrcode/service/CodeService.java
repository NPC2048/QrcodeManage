package com.liangyuelong.qrcode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liangyuelong.qrcode.common.form.code.CodeAddForm;
import com.liangyuelong.qrcode.common.form.code.CodeEditForm;
import com.liangyuelong.qrcode.entity.Code;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * 二维码 service
 *
 * @author yuelong.liang
 */
public interface CodeService extends IService<Code> {
    /**
     * 存储新的二维码
     *
     * @param form form
     * @return boolean
     */
    boolean save(CodeAddForm form);

    /**
     * 编辑二维码
     *
     * @param form form
     * @return boolean
     */
    boolean update(CodeEditForm form);

    /**
     * 根据用户名查找 code
     *
     * @param username username
     * @return List
     */
    List<Code> listByUsername(String username);

}
