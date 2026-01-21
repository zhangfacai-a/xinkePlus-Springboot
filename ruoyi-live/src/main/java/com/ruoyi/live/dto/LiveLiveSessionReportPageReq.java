package com.ruoyi.live.dto;

import lombok.Data;

@Data
public class LiveLiveSessionReportPageReq {
    private Long sessionId;
    private String ownerName; // 模糊
    private String beginTime; // reportTime范围
    private String endTime;
}
