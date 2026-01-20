package com.ruoyi.live.domain;

import lombok.Data;

import java.util.Date;
@Data
public class RoomUserRoom {
    private Long id;
    private String roomKey;
    private String shopName;
    private Date lastSeenTime;
}
