package com.ruoyi.live.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LiveLiveRoomPageResp {

    @Excel(name = "直播间ID")
    private Long roomId;

    @Excel(name = "直播间名称")
    private String roomName;

    @Excel(name = "场次数")
    private Long sessionCount;

    @Excel(name = "负责人数量")
    private Long ownerCount;

    @Excel(name = "最近上报时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastReportTime;

    @Excel(name = "成交金额")
    private BigDecimal gmvAmountTotal;

    @Excel(name = "成交件数")
    private Long dealItemCntTotal;

    @Excel(name = "退款金额")
    private BigDecimal refundAmountTotal;

    @Excel(name = "曝光次数")
    private Long exposureCntTotal;

    @Excel(name = "累计观看人数")
    private Long viewerCntTotal;

    @Excel(name = "新增粉丝数")
    private Long newFansCntTotal;

    @Excel(name = "成交人数")
    private Long dealUserCntTotal;

    @Excel(name = "千川消耗")
    private BigDecimal qianchuanCostTotal;
}
