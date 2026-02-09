package com.ruoyi.live.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRoomRoomVO {
    private Long id;
    private String roomKey;
    private String roomName;
    private LocalDateTime lastSeenTime;
}
