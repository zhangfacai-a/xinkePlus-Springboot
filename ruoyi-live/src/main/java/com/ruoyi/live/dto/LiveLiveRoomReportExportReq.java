package com.ruoyi.live.dto;

import lombok.Data;

@Data
public class LiveLiveRoomReportExportReq {
    private String roomName;
    private String ownerName;
    private String beginTime;
    private String endTime;
}
