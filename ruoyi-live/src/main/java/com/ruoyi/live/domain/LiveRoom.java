package com.ruoyi.live.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LiveRoom {
    private Long id;
    private String name;
    private LocalDateTime createTime;
}
