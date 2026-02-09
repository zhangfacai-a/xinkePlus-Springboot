package com.ruoyi.live.vo;

import lombok.Data;

@Data
public class UserRoomOwnerStatRow {
    private String ownerName;
    private Long followUserCount;
    private Long orderUserCount;
    private Long followActionCount;
}
