package com.ruoyi.live.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LiveLiveSessionReportPageResp {

    private Long reportId;
    private String ownerName;

    /** 本次上报时间（区间结束） */
    private Date reportTime;

    /** 上一次用于相减的上报时间（区间开始；来自视图 prev_report_time） */
    private Date lastReportTime;

    /** 区间分钟数 = reportTime - lastReportTime（无上一条则 0） */
    private Integer diffMinutes;

    // ===================== 以下指标均为“区间增量”口径（delta） =====================

    /** 直播间成交金额（区间增量） */
    private BigDecimal gmvAmount;

    /** 成交件数（区间增量） */
    private Integer dealItemCnt;

    /** 退款金额（区间增量） */
    private BigDecimal refundAmount;

    /** 曝光次数（区间增量） */
    private Long exposureCnt;

    /** 累计观看人数（区间增量） */
    private Long viewerCnt;

    /** 新增粉丝数（区间增量） */
    private Long newFansCnt;

    /** 成交人数（区间增量） */
    private Long dealUserCnt;

    /** 千川消耗（区间增量） */
    private BigDecimal qianchuanCost;

    // ===================== 比率（基于区间增量计算） =====================

    /**
     * 曝光点击进入率（区间）
     * = viewerCnt / exposureCnt（exposureCnt=0 时为 0）
     *
     * 说明：字段名沿用历史 exposureWatchRate，但当前口径已改为“区间增量比率”。
     */
    private BigDecimal exposureWatchRate;

    /**
     * 观看-关注率
     * 说明：你当前的 delta 视图里没有这个“人数转化”定义（原来是上报值）。
     * 如果你 Mapper 里返回 NULL，这里也允许为空。
     */
    private BigDecimal watchFollowRate;

    /**
     * 转化率（区间）
     * = dealUserCnt / viewerCnt（viewerCnt=0 时为 0）
     */
    private BigDecimal conversionRate;
}
