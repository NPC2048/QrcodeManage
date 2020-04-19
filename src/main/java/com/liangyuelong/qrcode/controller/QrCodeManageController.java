package com.liangyuelong.qrcode.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liangyuelong.qrcode.aop.annotation.Log;
import com.liangyuelong.qrcode.common.BizException;
import com.liangyuelong.qrcode.common.NoLogException;
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
import javax.validation.constraints.NotNull;
import java.util.List;

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
     * 查看二维码
     * 只能查看自己的
     *
     * @param id 二维码 id
     */
    @PreAuthorize("principal.username.equals(#userService.getById(#id).username)")
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
    public Page<Code> search(CodePageForm form) {
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
        return codeService.page(form, wrapper);
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
    public List<Code> content(String username) {
        return this.codeService.listByUsername(username);
    }

    /**
     * 添加二维码
     * 只能给自己添加
     */
    @PreAuthorize("authentication.details.id.equals(#form.userId)")
    @PostMapping("/add")
    @ResponseBody
    public void add(@Valid CodeAddForm form) {
        boolean result = this.codeService.save(form);
        if (!result) {
            throw new NoLogException("保存二维码失败, 请重试");
        }
    }

    /**
     * 编辑二维码
     * 只能编辑自己的
     */
    @PreAuthorize("authentication.details.id.equals(#form.userId)")
    @PostMapping("/edit")
    @ResponseBody
    public void edit(@Valid CodeEditForm form) {
        if (!this.codeService.update(form)) {
            throw new NoLogException("编辑失败, 请重试");
        }
    }

    /**
     * 删除二维码
     * 只能删除自己的
     *
     * @param id 二维码 id
     */
    @PreAuthorize("authentication.details.id.equals(#id)")
    @PostMapping("/delete")
    @ResponseBody
    public void delete(@Valid @NotNull(message = "id 不能为空") Long id) {
        codeService.removeById(id);
    }

}
