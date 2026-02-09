package com.ruoyi.live.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRoomAudienceVO {

    private Long roomId;
    private Long userId;

    // 用户基础
    private String secUid;
    private String nickname;
    private LocalDateTime userLastSeenTime;

    // 采集字段
    private Boolean isFollower;
    private Boolean isFollowing;
    /** 最近一次上报增量（秒） */
    private Integer lastWatchTimeDelta;
    /** 采集更新时间 */
    private LocalDateTime captureUpdateTime;

    // 人工字段
    private String ownerName;
    private Integer followStatus;
    private String orderNo;
    private String remark;
    private Integer intentionLevel;
    /** 人工更新时间 */
    private LocalDateTime manageUpdateTime;
    private Integer watchTimeTodayCum; // 今日累计观看时长(秒)

    // 来访统计
    private Long visitCount;
    private LocalDateTime lastVisitTime;

    // 当前负责人接手时间（取 owner_history 最大值）
    private LocalDateTime ownerAssignTime;
}
