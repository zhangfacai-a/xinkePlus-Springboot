package com.ruoyi.live.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * room_user_relation 直播间用户关系表
 *
 * 【备注】
 * 这张表分两类字段：
 * 1) 采集字段（插件上报会更新）：isFollower/isFollowing/lastWatchTime
 * 2) 业务字段（系统里人工维护）：ownerName/followStatus/orderNo/remark
 *
 * 关键：采集入库不要覆盖业务字段。
 */
@Data
public class RoomUserRelation {
    private Long id;

    private Long roomId;
    private Long userId;

    /** 负责人（人工维护） */
    private String ownerName;

    /** 跟单状态：0未跟单 1跟单中 2已下单 3未回复（人工维护） */
    private Integer followStatus;

    /** 订单号（followStatus=2 时必填，人工维护） */
    private String orderNo;

    /** 备注（人工维护） */
    private String remark;

    /** 是否关注主播（采集字段） */
    private Boolean isFollower;

    /** 主播是否关注用户（采集字段） */
    private Boolean isFollowing;

    /** 最近观看时长（秒）（采集字段） */
    private Integer lastWatchTime;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
