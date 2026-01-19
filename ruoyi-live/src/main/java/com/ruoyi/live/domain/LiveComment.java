package com.ruoyi.live.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LiveComment {
    private Long id;
    private Long roomId;
    private Long userId;
    private String content;
    private LocalDateTime createTime;
}
