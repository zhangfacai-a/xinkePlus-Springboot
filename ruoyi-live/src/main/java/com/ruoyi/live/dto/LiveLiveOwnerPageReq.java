package com.ruoyi.live.dto;

import lombok.Data;

@Data
public class LiveLiveOwnerPageReq {
    private String ownerName; // 模糊
    private String beginTime; // reportTime范围
    private String endTime;
}
