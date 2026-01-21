package com.ruoyi.live.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LiveLiveOwnerRoomPageResp {
    private Long roomId;
    private String roomName;

    private Long sessionCount;
    private Date lastReportTime;

    private BigDecimal gmvAmountTotal;
    private Long dealItemCntTotal;
    private BigDecimal refundAmountTotal;
    private Long exposureCntTotal;
    private Long viewerCntTotal;
    private Long newFansCntTotal;
    private Long dealUserCntTotal;
    private BigDecimal qianchuanCostTotal;
}
