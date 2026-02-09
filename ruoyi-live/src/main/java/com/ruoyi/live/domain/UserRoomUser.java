package com.ruoyi.live.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserRoomUser {
    private Long id;
    private String secUid;
    private String nickname;
    private LocalDateTime lastSeenTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
