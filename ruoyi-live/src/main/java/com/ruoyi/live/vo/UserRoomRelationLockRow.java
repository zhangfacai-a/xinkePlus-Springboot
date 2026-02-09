package com.ruoyi.live.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRoomRelationLockRow {
    private Long roomId;
    private Long userId;
    private String ownerName;
    private LocalDateTime ownerAssignTime;
}
