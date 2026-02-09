package com.ruoyi.live.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserRoomAudienceDetailVO {

    /** 基础信息（同列表字段） */
    private UserRoomAudienceVO base;

    /** 跟进历史 */
    private List<UserRoomFollowHistoryVO> followHistory;

    /** 负责人交接历史 */
    private List<UserRoomOwnerHistoryVO> ownerHistory;
}
