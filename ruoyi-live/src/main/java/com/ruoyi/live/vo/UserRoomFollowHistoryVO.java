package com.ruoyi.live.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRoomFollowHistoryVO {
    private String ownerName;
    private Integer followStatus;
    private String orderNo;
    private String remark;
    private Integer intentionLevel;
    private LocalDateTime followTime;
}
