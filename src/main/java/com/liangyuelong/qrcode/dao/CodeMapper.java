package com.liangyuelong.qrcode.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangyuelong.qrcode.entity.Code;

import java.util.List;

/**
 * 二维码 service
 *
 * @author yuelong.liang
 */
public interface CodeMapper extends BaseMapper<Code> {

    List<Code> listByUsername(String username);

}
