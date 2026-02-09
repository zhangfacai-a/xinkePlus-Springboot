package com.ruoyi.live.domain;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserRoomVisitDaily {
    private Long id;
    private Long roomId;
    private Long userId;
    private LocalDate visitDate;
    private LocalDateTime lastVisitTime;
    private LocalDateTime createTime;
}
