package com.ruoyi.live.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LiveLiveRoomSessionPageResp {
    private Long sessionId;
    private String platformSessionRoomId; // 上报roomId
    private Date startTime;

    private Long ownerCount;
    private Date lastReportTime;

    // 汇总（最后快照口径）
    private BigDecimal gmvAmountTotal;
    private Long dealItemCntTotal;
    private BigDecimal refundAmountTotal;
    private Long exposureCntTotal;
    private Long viewerCntTotal;
    private Long newFansCntTotal;
    private Long dealUserCntTotal;
    private BigDecimal qianchuanCostTotal;
}
