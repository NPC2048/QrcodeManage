package com.liangyuelong.qrcode.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liangyuelong.qrcode.aop.annotation.Log;
import com.liangyuelong.qrcode.common.BizException;
import com.liangyuelong.qrcode.common.NoLogException;
import com.liangyuelong.qrcode.common.bean.R;
import com.liangyuelong.qrcode.common.form.code.CodeAddForm;
import com.liangyuelong.qrcode.common.form.code.CodeEditForm;
import com.liangyuelong.qrcode.common.form.code.CodePageForm;
import com.liangyuelong.qrcode.entity.Code;
import com.liangyuelong.qrcode.service.CodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 二维码管理 controller
 *
 * @author yuelong.liang
 */
@Controller
@RequestMapping("/manage")
@Log
public class QrCodeManageController {

    @Resource
    private CodeService codeService;


    /**
     * 查询二维码
     * <p>
     * 前端根据 base_url 显示二维码
     *
     * @return R
     */
    @PreAuthorize("authentication.details.id.equals(#form.userId)")
    @GetMapping("/search")
    @ResponseBody
    public R search(CodePageForm form) {
        QueryWrapper<Code> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(form.getName())) {
            wrapper.like("name", form.getName());
        }
        if (form.getUserId() != null) {
            wrapper.eq("user_id", form.getUserId());
        }
        if (StringUtils.isNotEmpty(form.getContent())) {
            wrapper.like("content", form.getContent());
        }
        return R.success(codeService.page(form, wrapper));
    }

    /**
     * 查询所有二维码
     * 只能查询自己的
     *
     * @return List
     */
    @PreAuthorize("principal.equals(#username)")
    @GetMapping("/content")
    @ResponseBody
    public R content(String username) {
        return R.success(this.codeService.listByUsername(username));
    }

    /**
     * 添加二维码
     * 只能给自己添加
     */
    @PreAuthorize("authentication.details.id.equals(#form.userId)")
    @PostMapping("/add")
    @ResponseBody
    public R add(@Valid CodeAddForm form) {
        boolean result = this.codeService.save(form);
        if (!result) {
            throw new NoLogException("保存二维码失败, 请重试");
        }
        return R.SUCCESS;
    }

    /**
     * 编辑二维码
     * 只能编辑自己的
     */
    @PreAuthorize("authentication.details.id.equals(#form.userId)")
    @PostMapping("/edit")
    @ResponseBody
    public R edit(@Valid CodeEditForm form) {
        if (!this.codeService.update(form)) {
            throw new NoLogException("编辑失败, 请重试");
        }
        return R.SUCCESS;
    }

    /**
     * 删除二维码
     * 只能删除自己的
     *
     * @param id 二维码 id
     */
    @PreAuthorize("authentication.details.id.equals(#userId)")
    @PostMapping("/delete")
    @ResponseBody
    public R delete(Long userId, Long id) {
        if (id == null) {
            throw new NoLogException("id 不能为空");
        }
        if (id < 1) {
            throw new NoLogException("id 不合法");
        }
        if (!codeService.removeById(id)) {
            throw new NoLogException("删除二维码失败, 请重试");
        }
        return R.SUCCESS;
    }

}
