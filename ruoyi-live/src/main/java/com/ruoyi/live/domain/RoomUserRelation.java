package com.ruoyi.live.domain;

public class RoomUserRelation {
    private Long id;
    private Long roomId;
    private Long userId;
    private Integer isFollower;   // 0/1
    private Integer isFollowing;  // 0/1
    private Integer lastWatchTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Integer getIsFollower() { return isFollower; }
    public void setIsFollower(Integer isFollower) { this.isFollower = isFollower; }

    public Integer getIsFollowing() { return isFollowing; }
    public void setIsFollowing(Integer isFollowing) { this.isFollowing = isFollowing; }

    public Integer getLastWatchTime() { return lastWatchTime; }
    public void setLastWatchTime(Integer lastWatchTime) { this.lastWatchTime = lastWatchTime; }
}
