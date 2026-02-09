package com.ruoyi.live.dto;

import lombok.Data;

/** 直播间用户列表筛选条件 */
@Data
public class UserRoomAudienceQuery {

    private String roomKey; // 用 roomKey 查询（后台更常用）
    private Long roomId;    // 也支持直接传 roomId

    private String secUid;
    private String nickname;

    /** 负责人：当前负责人 + 历史负责人（like 模糊匹配） */
    private String ownerName;

    private Integer followStatus;
    private Integer intentionLevel;

    /** 关注状态筛选（null 不筛） */
    private Boolean isFollower;
    private Boolean isFollowing;

    /** 采集更新时间区间（capture_update_time） */
    private String captureTimeBegin;
    private String captureTimeEnd;

    /** 人工更新时间区间（manage_update_time） */
    private String manageTimeBegin;
    private String manageTimeEnd;
}
