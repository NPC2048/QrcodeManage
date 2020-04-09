package com.liangyuelong.qrcode.common.bean;

import com.liangyuelong.qrcode.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回值
 *
 * @author yuelong.liang
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class R {

    public static final R SUCCESS;

    public static final R FAILED;

    static {
        SUCCESS = new R(ResultEnum.SUCCESS);
        FAILED = new R(ResultEnum.FAILED);
    }

    protected byte state;

    protected String msg;

    protected Object obj;

    public R(ResultEnum state) {
        this.state = state.getCode();
    }

    public R(ResultEnum state, String msg, Object obj) {
        this.state = state.getCode();
        this.msg = msg;
        this.obj = obj;
    }

    public R(byte state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public R(byte state, Object obj) {
        this.state = state;
        this.obj = obj;
    }

    public R(byte state) {
        this.state = state;
    }

    public static R success(String msg) {
        return new R(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static R success(Object obj) {
        return new R(ResultEnum.SUCCESS.getCode(), obj);
    }

    public static R failed(String msg) {
        return new R(ResultEnum.FAILED.getCode(), msg);
    }

    public static R state(boolean state) {
        return new R(state ? ResultEnum.SUCCESS.getCode() : ResultEnum.FAILED.getCode());
    }

}
