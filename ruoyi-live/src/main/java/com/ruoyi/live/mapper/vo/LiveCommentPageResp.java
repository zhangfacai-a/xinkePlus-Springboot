package com.ruoyi.live.mapper.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LiveCommentPageResp {
    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime createTime;
}
