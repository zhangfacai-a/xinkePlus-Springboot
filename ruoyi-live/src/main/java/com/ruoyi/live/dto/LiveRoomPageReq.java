package com.ruoyi.live.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
public class LiveRoomPageReq {
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;


    /** 排序方式：userCountDesc/userCountAsc/createTimeDesc/createTimeAsc/lastActiveDesc/lastActiveAsc */
    private String sortBy;
}
