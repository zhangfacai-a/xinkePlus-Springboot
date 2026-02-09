package com.ruoyi.live.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UserRoomAudienceExportRow {

    @ExcelProperty("SecUid")
    private String secUid;

    @ExcelProperty("昵称")
    private String nickname;

    @ExcelProperty("用户关注主播")
    private String isFollower;

    @ExcelProperty("主播关注用户")
    private String isFollowing;

    @ExcelProperty("最近一次观看增量(秒)")
    private Integer lastWatchTimeDelta;

    @ExcelProperty("负责人")
    private String ownerName;

    @ExcelProperty("跟单状态")
    private String followStatusText;

    @ExcelProperty("订单号")
    private String orderNo;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("意向等级")
    private String intentionLevelText;

    @ExcelProperty("来过次数")
    private Long visitCount;

    @ExcelProperty("最近来访时间")
    private String lastVisitTime;

    @ExcelProperty("负责人接手时间")
    private String ownerAssignTime;

    @ExcelProperty("采集更新时间")
    private String captureUpdateTime;

    @ExcelProperty("人工更新时间")
    private String manageUpdateTime;
}
