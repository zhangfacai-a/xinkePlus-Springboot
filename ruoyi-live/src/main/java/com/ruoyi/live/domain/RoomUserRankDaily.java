package com.ruoyi.live.domain;

import java.util.Date;

public class RoomUserRankDaily {
    private Long id;
    private Long roomId;
    private Long userId;
    private Date statDate;       // 用 java.util.Date 存 DATE 也可以（xml 里用 jdbcType=DATE）
    private Integer watchTime;
    private Date capturedTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Date getStatDate() { return statDate; }
    public void setStatDate(Date statDate) { this.statDate = statDate; }

    public Integer getWatchTime() { return watchTime; }
    public void setWatchTime(Integer watchTime) { this.watchTime = watchTime; }

    public Date getCapturedTime() { return capturedTime; }
    public void setCapturedTime(Date capturedTime) { this.capturedTime = capturedTime; }
}
