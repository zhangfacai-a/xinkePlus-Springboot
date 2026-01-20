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

    /**
     * 关系更新时间-开始（筛选 room_user_relation.update_time）
     * 建议前端传：yyyy-MM-dd HH:mm:ss
     */
    private String relationUpdateTimeBegin;

    /**
     * 关系更新时间-结束（筛选 room_user_relation.update_time）
     * 建议前端传：yyyy-MM-dd HH:mm:ss
     */
    private String relationUpdateTimeEnd;

    public String getSecUid() { return secUid; }
    public void setSecUid(String secUid) { this.secUid = secUid; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public Integer getFollowStatus() { return followStatus; }
    public void setFollowStatus(Integer followStatus) { this.followStatus = followStatus; }

    public String getRelationUpdateTimeBegin() { return relationUpdateTimeBegin; }
    public void setRelationUpdateTimeBegin(String relationUpdateTimeBegin) { this.relationUpdateTimeBegin = relationUpdateTimeBegin; }

    public String getRelationUpdateTimeEnd() { return relationUpdateTimeEnd; }
    public void setRelationUpdateTimeEnd(String relationUpdateTimeEnd) { this.relationUpdateTimeEnd = relationUpdateTimeEnd; }
}
