package com.ruoyi.live.dto;

import lombok.Data;

@Data
public class LiveLiveOwnerRoomReportPageReq {
    private Long ownerId;
    private Long roomId;
    private String beginTime; // reportTime范围
    private String endTime;
}
