package com.ruoyi.live.domain;

import java.util.Date;

public class RoomUserRoom {
    private Long id;
    private String roomKey;
    private String shopName;
    private Date lastSeenTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomKey() { return roomKey; }
    public void setRoomKey(String roomKey) { this.roomKey = roomKey; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public Date getLastSeenTime() { return lastSeenTime; }
    public void setLastSeenTime(Date lastSeenTime) { this.lastSeenTime = lastSeenTime; }
}
