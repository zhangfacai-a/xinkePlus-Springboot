package com.ruoyi.live.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LiveLiveMetricsReportReq {

    /** 每场唯一（必填） */
    private String roomId;

    /** 直播间名称（必填） */
    private String roomName;

    /** 开播时间（必填） */
    private String startTime;

    /** 负责人（必填） */
    private String ownerName;

    /** 指标（允许为空，后端兜底为0） */
    private BigDecimal gmvAmount;
    private Integer dealItemCnt;
    private BigDecimal refundAmount;
    private Long exposureCnt;
    private Long viewerCnt;
    private Long newFansCnt;
    private Long dealUserCnt;
    private BigDecimal qianchuanCost;

    /** 直接上报的比率（可空） */
    private BigDecimal exposureWatchRate;
    private BigDecimal watchFollowRate;

    /** 本次上报时间（必填） */
    private String reportTime;
}
