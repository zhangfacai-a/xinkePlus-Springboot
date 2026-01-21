package com.ruoyi.live.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LiveLiveTimeParser {

    private static final DateTimeFormatter[] FMT = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    };

    public static LocalDateTime parse(String s) {
        if (s == null || s.isBlank()) throw new IllegalArgumentException("时间不能为空");
        String t = s.trim();
        for (DateTimeFormatter f : FMT) {
            try {
                return LocalDateTime.parse(t, f);
            } catch (Exception ignore) {}
        }
        throw new IllegalArgumentException("无法解析时间: " + s);
    }
}
