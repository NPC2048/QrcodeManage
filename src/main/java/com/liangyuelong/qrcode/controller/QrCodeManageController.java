package com.liangyuelong.qrcode.controller;

import com.liangyuelong.qrcode.common.bean.R;
import com.liangyuelong.qrcode.service.CodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 二维码管理 controller
 *
 * @author yuelong.liang
 */
@RestController
@RequestMapping("/manage")
public class QrCodeManageController {

    @Resource
    private CodeService codeService;

    /**
     * 查询二维码
     *
     * @return R
     */
    @GetMapping("/search")
    public R search() {
        return R.SUCCESS;
    }

    /**
     * 添加二维码
     *
     * @return R
     */
    @PostMapping("/add")
    public R add() {
        return R.SUCCESS;
    }

    /**
     * 编辑二维码
     *
     * @return R
     */
    @PostMapping("/edit")
    public R edit() {
        return R.SUCCESS;
    }

    /**
     * 删除二维码
     *
     * @param id 二维码 id
     * @return R
     */
    @PostMapping("/delete")
    public R delete(@Valid @NotNull(message = "id 不能为空") Long id) {
        codeService.removeById(id);
        return R.SUCCESS;
    }

}
