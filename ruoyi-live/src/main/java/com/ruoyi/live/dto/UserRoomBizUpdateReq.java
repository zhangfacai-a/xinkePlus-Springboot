package com.ruoyi.live.dto;

import lombok.Data;

/** 人工维护更新请求 */
@Data
public class UserRoomBizUpdateReq {

    private Long roomId;
    private Long userId;

    /** 负责人（自由文本；不允许清空） */
    private String ownerName;

    /** 0未跟单 1跟单中 2已下单 3未回复 */
    private Integer followStatus;

    /** 已下单必须填；其他状态自动清空 */
    private String orderNo;

    private String remark;

    /** 1重点 2中级 3初级；默认可空 */
    private Integer intentionLevel;
}
