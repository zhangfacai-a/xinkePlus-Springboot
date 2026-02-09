package com.ruoyi.live.domain;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserRoomRankDaily {
    private Long id;
    private Long roomId;
    private Long userId;
    private LocalDate statDate;
    private Integer watchTimeCum;
    private LocalDateTime capturedTime;
    private LocalDateTime createTime;
}
