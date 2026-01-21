package com.ruoyi.live.dto;

import lombok.Data;

@Data
public class LiveLiveOwnerRoomPageReq {
    private Long ownerId;
    private String roomName;  // 模糊
    private String beginTime; // reportTime范围
    private String endTime;
}
