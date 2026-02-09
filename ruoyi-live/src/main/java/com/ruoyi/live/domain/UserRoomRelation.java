package com.ruoyi.live.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserRoomRelation {
    private Long id;
    private Long roomId;
    private Long userId;

    private Boolean isFollower;
    private Boolean isFollowing;
    private Integer lastWatchTimeDelta;
    private LocalDateTime captureUpdateTime;

    // 人工字段（采集 upsert 不更新）
    private String ownerName;
    private LocalDateTime ownerAssignTime;
    private Integer followStatus;
    private String orderNo;
    private String remark;
    private Integer intentionLevel;
    private LocalDateTime manageUpdateTime;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
