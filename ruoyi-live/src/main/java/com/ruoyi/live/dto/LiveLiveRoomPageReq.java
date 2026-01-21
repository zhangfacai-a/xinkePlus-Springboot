package com.ruoyi.live.dto;

import lombok.Data;

@Data
public class LiveLiveRoomPageReq {
    private String roomName;   // 模糊
    private String ownerName;  // 模糊（可选：按负责人筛直播间）
    private String beginTime;  // reportTime范围
    private String endTime;
}
