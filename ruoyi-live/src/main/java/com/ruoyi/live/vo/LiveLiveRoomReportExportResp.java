package com.ruoyi.live.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LiveLiveRoomReportExportResp {

    // ===== 维度信息 =====
    @Excel(name = "直播间")
    private String roomName;

    @Excel(name = "平台场次ID")
    private String platformSessionRoomId;

    @Excel(name = "负责人")
    private String ownerName;

//    @Excel(name = "场次ID(sessionId)")
//    private Long sessionId;
//
//    @Excel(name = "负责人ID(ownerId)")
//    private Long ownerId;

    // ===== 时间信息 =====
    @Excel(name = "本次上报时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reportTime;

    @Excel(name = "上次上报时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date prevReportTime;

    @Excel(name = "间隔(分钟)")
    private Long diffMinutes;

    // ===== 累计 + 本段Δ =====
    @Excel(name = "曝光(累计)")
    private Long exposureCnt;
    @Excel(name = "曝光(本段Δ)")
    private Long exposureCntDelta;

    @Excel(name = "观看(累计)")
    private Long viewerCnt;
    @Excel(name = "观看(本段Δ)")
    private Long viewerCntDelta;

    @Excel(name = "新增粉丝(累计)")
    private Long newFansCnt;
    @Excel(name = "新增粉丝(本段Δ)")
    private Long newFansCntDelta;

    @Excel(name = "成交人数(累计)")
    private Long dealUserCnt;
    @Excel(name = "成交人数(本段Δ)")
    private Long dealUserCntDelta;

    @Excel(name = "成交件数(累计)")
    private Long dealItemCnt;
    @Excel(name = "成交件数(本段Δ)")
    private Long dealItemCntDelta;

    @Excel(name = "成交金额(累计)")
    private BigDecimal gmvAmount;
    @Excel(name = "成交金额(本段Δ)")
    private BigDecimal gmvAmountDelta;

    @Excel(name = "退款金额(累计)")
    private BigDecimal refundAmount;
    @Excel(name = "退款金额(本段Δ)")
    private BigDecimal refundAmountDelta;

    @Excel(name = "千川消耗(累计)")
    private BigDecimal qianchuanCost;
    @Excel(name = "千川消耗(本段Δ)")
    private BigDecimal qianchuanCostDelta;
    /** 曝光-观看率(次数)（上报值） */
    @Excel(name = "曝光-观看率(累计上报值)", scale = 6)
    private BigDecimal exposureWatchRate;

    /** 观看-关注率(人数)（上报值） */
    @Excel(name = "观看-关注率(累计上报值)", scale = 6)
    private BigDecimal watchFollowRate;

    /** 曝光-观看率（本段Δ：viewerΔ/exposureΔ） */
    @Excel(name = "曝光-观看率(本段Δ)", scale = 6)
    private BigDecimal exposureWatchRateDelta;

    /** 观看-关注率（本段Δ：newFansΔ/viewerΔ） */
    @Excel(name = "观看-关注率(本段Δ)", scale = 6)
    private BigDecimal watchFollowRateDelta;
    // ===== 率：累计口径 & 本段Δ口径 =====
    @Excel(name = "转化率(累计)", scale = 6)
    private BigDecimal conversionRate;

    @Excel(name = "曝光点击进入率(累计)", scale = 6)
    private BigDecimal exposureClickRate;

    @Excel(name = "转化率(本段Δ)", scale = 6)
    private BigDecimal conversionRateDelta;

    @Excel(name = "曝光点击进入率(本段Δ)", scale = 6)
    private BigDecimal exposureClickRateDelta;
}
