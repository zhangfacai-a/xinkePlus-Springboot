package com.ruoyi.live.domain;

import java.util.Date;

public class RoomUserUser {
    private Long id;
    private String secUid;
    private String nickname;
    private Date lastSeenTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSecUid() { return secUid; }
    public void setSecUid(String secUid) { this.secUid = secUid; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public Date getLastSeenTime() { return lastSeenTime; }
    public void setLastSeenTime(Date lastSeenTime) { this.lastSeenTime = lastSeenTime; }
}
