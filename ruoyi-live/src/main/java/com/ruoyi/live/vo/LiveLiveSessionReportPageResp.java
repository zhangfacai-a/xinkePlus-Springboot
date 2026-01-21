package com.ruoyi.live.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LiveLiveSessionReportPageResp {
    private Long reportId;
    private String ownerName;

    private Date reportTime;
    private Date lastReportTime;
    private Integer diffMinutes;

    private BigDecimal gmvAmount;
    private Integer dealItemCnt;
    private BigDecimal refundAmount;
    private Long exposureCnt;
    private Long viewerCnt;
    private Long newFansCnt;
    private Long dealUserCnt;
    private BigDecimal qianchuanCost;

    private BigDecimal exposureWatchRate;
    private BigDecimal watchFollowRate;
    private BigDecimal conversionRate;
}
