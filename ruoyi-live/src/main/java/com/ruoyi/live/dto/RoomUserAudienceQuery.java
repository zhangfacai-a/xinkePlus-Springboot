package com.ruoyi.live.dto;

/**
 * 直播间用户列表筛选条件
 *
 * 【备注】只用于系统查询，不参与插件上报入库。
 */
public class RoomUserAudienceQuery {
    private String secUid;
    private String nickname;
    private String ownerName;
    private Integer followStatus;

    public String getSecUid() { return secUid; }
    public void setSecUid(String secUid) { this.secUid = secUid; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public Integer getFollowStatus() { return followStatus; }
    public void setFollowStatus(Integer followStatus) { this.followStatus = followStatus; }
}
