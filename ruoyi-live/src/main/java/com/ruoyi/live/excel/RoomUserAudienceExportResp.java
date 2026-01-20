package com.ruoyi.live.excel;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 直播间用户导出 VO（系统用）
 *
 * 【备注】
 * - 用于 ExcelUtil 导出（优先使用 @Excel 注解）
 * - 字段尽量和列表页一致，但对布尔值做了“是/否”转换，更适合给运营看
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

    @Excel(name = "状态码")
    private Integer followStatus;

    @Excel(name = "订单号")
    private String orderNo;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "关系更新时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime relationUpdateTime;
}
