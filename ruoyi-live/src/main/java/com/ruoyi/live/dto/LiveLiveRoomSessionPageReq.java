package com.ruoyi.live.dto;

import lombok.Data;

@Data
public class LiveLiveRoomSessionPageReq {
    private Long roomId;      // 固定直播间id
    private String beginTime; // reportTime范围
    private String endTime;
}
