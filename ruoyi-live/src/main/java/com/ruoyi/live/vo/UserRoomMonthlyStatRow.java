package com.ruoyi.live.vo;

import lombok.Data;

@Data
public class UserRoomMonthlyStatRow {
    private String month; // yyyy-MM
    private Long followUserCount;
    private Long orderUserCount;
    private Long followActionCount;
}
