package com.ruoyi.live.mapper.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class LiveRoomPageResp {
    private Long id;
    private String name;
    private Integer userCount;          // 用户总数

    // 新增：4个状态数量
    private Integer status0Count;       // 未处理
    private Integer status1Count;       // 已跟进
    private Integer status2Count;       // 已成交
    private Integer status3Count;       // 无效线索

    /** 首次出现时间（直播间创建时间） */
    private LocalDateTime createTime;

    /** 最近活跃时间（最后一条评论时间） */
    private LocalDateTime lastActiveTime;
}
