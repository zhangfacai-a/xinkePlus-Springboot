package com.ruoyi.live.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LiveUser {
    private Long id;
    private Long roomId;
    private String username;
    private Integer status;
    private LocalDateTime createTime;
}
