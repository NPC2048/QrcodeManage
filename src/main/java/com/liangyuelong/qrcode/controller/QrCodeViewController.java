package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.common.BizException;
import com.liangyuelong.qrcode.entity.Code;
import com.liangyuelong.qrcode.service.CodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * 二维码查看与计数
 *
 * @author yuelong.liang
 */
@Controller
public class QrCodeViewController {

    @Resource
    private CodeService codeService;

    /**
     * 查看二维码
     * 只能查看自己的
     *
     * @param id 二维码 id
     */
    @GetMapping(value = "/view")
    public String view(Long id, ModelMap modelMap) {
        if (id == null) {
            throw new BizException("id 不能为空");
        }
        Code code = this.codeService.getById(id);
        if (code == null) {
            throw new BizException("该二维码不存在");
        }
        // 查看次数 +1
        code.setViewNum(code.getViewNum() + 1);
        this.codeService.updateById(code);
        modelMap.put("obj", code);
        return "jump";
    }

}
