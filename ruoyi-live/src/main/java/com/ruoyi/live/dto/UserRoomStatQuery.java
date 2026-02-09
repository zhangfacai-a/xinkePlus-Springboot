package com.ruoyi.live.dto;

import lombok.Data;

/** 统计查询参数（前端默认传本月 begin/end，也可不传由前端控制） */
@Data
public class UserRoomStatQuery {

    /** 二选一：roomKey 或 roomId；都不传表示全量 */
    private String roomKey;
    private Long roomId;

    /** 时间范围：yyyy-MM-dd HH:mm:ss（建议前端传） */
    private String beginTime;
    private String endTime;

    /** 是否包含负责人为空的记录（默认 true 归到“未分配”） */
    private Boolean includeUnassigned = true;
}
