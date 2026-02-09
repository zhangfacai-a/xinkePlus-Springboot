package com.ruoyi.live.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserRoomRoom {
    private Long id;
    private String roomKey;
    private String roomName;
    private LocalDateTime lastSeenTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
