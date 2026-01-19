package com.ruoyi.live.vo;

import java.time.LocalDateTime;

/**
 * 直播间用户列表 VO
 *
 * 【备注】联表输出：room_user_relation + room_user_user
 */
public class RoomUserAudienceVO {
    private Long roomId;
    private Long userId;

    private String secUid;
    private String nickname;
    private LocalDateTime userLastSeenTime;

    private Boolean isFollower;
    private Boolean isFollowing;
    private Integer lastWatchTime;

    private String ownerName;
    private Integer followStatus;
    private String orderNo;
    private String remark;

    private LocalDateTime relationUpdateTime;

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getSecUid() { return secUid; }
    public void setSecUid(String secUid) { this.secUid = secUid; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public LocalDateTime getUserLastSeenTime() { return userLastSeenTime; }
    public void setUserLastSeenTime(LocalDateTime userLastSeenTime) { this.userLastSeenTime = userLastSeenTime; }

    public Boolean getIsFollower() { return isFollower; }
    public void setIsFollower(Boolean isFollower) { this.isFollower = isFollower; }

    public Boolean getIsFollowing() { return isFollowing; }
    public void setIsFollowing(Boolean isFollowing) { this.isFollowing = isFollowing; }

    public Integer getLastWatchTime() { return lastWatchTime; }
    public void setLastWatchTime(Integer lastWatchTime) { this.lastWatchTime = lastWatchTime; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public Integer getFollowStatus() { return followStatus; }
    public void setFollowStatus(Integer followStatus) { this.followStatus = followStatus; }

    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public LocalDateTime getRelationUpdateTime() { return relationUpdateTime; }
    public void setRelationUpdateTime(LocalDateTime relationUpdateTime) { this.relationUpdateTime = relationUpdateTime; }
}
