package com.ruoyi.live.excel;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 直播间用户导出 VO（系统用）
 *
 * 【备注】
 * - 用于 ExcelUtil 导出
 * - 状态码通过 readConverterExp 转为中文
 */
@Data
public class RoomUserAudienceExportResp {

    @Excel(name = "SecUid")
    private String secUid;

    @Excel(name = "昵称")
    private String nickname;

    @Excel(name = "TA关注主播")
    private String isFollowingText;

    @Excel(name = "主播关注TA")
    private String isFollowerText;

    @Excel(name = "观看时长(秒)")
    private Integer lastWatchTime;

    @Excel(name = "负责人")
    private String ownerName;

    /** ✅ 状态码 → 中文 */
    @Excel(
            name = "状态",
            readConverterExp = "0=未跟单,1=跟单中,2=已下单,3=未回复"
    )
    private Integer followStatus;

    @Excel(name = "订单号")
    private String orderNo;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "关系更新时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime relationUpdateTime;
}
