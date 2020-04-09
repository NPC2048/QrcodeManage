package com.liangyuelong.qrcode.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ResultEnum {

    FAILED((byte) 0),
    SUCCESS((byte) 1);

    @Getter
    @Setter
    private byte code;

}
