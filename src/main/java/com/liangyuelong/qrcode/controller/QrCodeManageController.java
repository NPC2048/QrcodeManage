package com.liangyuelong.qrcode.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liangyuelong.qrcode.aop.annotation.Log;
import com.liangyuelong.qrcode.common.BizException;
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
    @PreAuthorize("hasRole('MANAGER')")
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
     * @return R
     */
    @GetMapping("/content")
    @ResponseBody
    public R content(String username) {
        if (StringUtils.isBlank(username)) {
            return R.failed("用户名不能为空");
        }
        List<Code> codeList = this.codeService.listByUsername(username);
        return R.success(codeList);
    }

    /**
     * 添加二维码
     * 只能给自己添加
     *
     * @return R
     */
    @PreAuthorize("authentication.details.id.equals(#form.userId)")
    @PostMapping("/add")
    @ResponseBody
    public R add(@Valid CodeAddForm form) {
        boolean result = this.codeService.save(form);
        if (!result) {
            return R.failed("保存二维码失败, 请重试");
        }
        return R.SUCCESS;
    }

    /**
     * 编辑二维码
     * 只能编辑自己的
     *
     * @return R
     */
    @PreAuthorize("principal.username.equals(#userService.getById(form.userId).username)")
    @PostMapping("/edit")
    @ResponseBody
    public R edit(@Valid CodeEditForm form) {
        if (!this.codeService.update(form)) {
            return R.failed("编辑失败, 请重试");
        }
        return R.SUCCESS;
    }

    /**
     * 删除二维码
     * 只能删除自己的
     *
     * @param id 二维码 id
     * @return R
     */
    @PreAuthorize("principal.username.equals(#userService.getById(id).username)")
    @PostMapping("/delete")
    @ResponseBody
    public R delete(@Valid @NotNull(message = "id 不能为空") Long id) {
        codeService.removeById(id);
        return R.SUCCESS;
    }

}
