package com.ruoyi.live.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRoomOwnerHistoryVO {
    private String ownerName;
    private LocalDateTime assignTime;
}
