package com.ruoyi.live.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LiveUserStatusEnum {
    UNHANDLED(0, "未处理"),
    FOLLOWED(1, "已跟进"),
    DEAL(2, "已成交"),
    INVALID(3, "无效线索");

    private final int code;
    private final String name;

    public static String nameOf(Integer code) {
        if (code == null) return "";
        for (LiveUserStatusEnum e : values()) {
            if (e.code == code) return e.name;
        }
        return "";
    }
}
