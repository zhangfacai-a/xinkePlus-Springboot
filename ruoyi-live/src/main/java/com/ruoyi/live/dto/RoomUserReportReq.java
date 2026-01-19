package com.ruoyi.live.dto;
import java.util.List;

public class RoomUserReportReq {

    /** 直播间唯一标识（建议传 anchor_id / room_id） */
    private String roomKey;

    /** 店铺名 */
    private String shopName;

    /** 榜单日期字符串：例如 2026.01.17 */
    private String readyDate;

    /** 榜单明细 */
    private List<Item> ranks;

    public static class Item {
        /** 秒 */
        private Integer watchTime;
        private String secUid;
        private String nickname;
        private Boolean isFollower;
        private Boolean isFollowing;

        public Integer getWatchTime() { return watchTime; }
        public void setWatchTime(Integer watchTime) { this.watchTime = watchTime; }

        public String getSecUid() { return secUid; }
        public void setSecUid(String secUid) { this.secUid = secUid; }

        public String getNickname() { return nickname; }
        public void setNickname(String nickname) { this.nickname = nickname; }

        public Boolean getIsFollower() { return isFollower; }
        public void setIsFollower(Boolean isFollower) { this.isFollower = isFollower; }

        public Boolean getIsFollowing() { return isFollowing; }
        public void setIsFollowing(Boolean isFollowing) { this.isFollowing = isFollowing; }
    }

    public String getRoomKey() { return roomKey; }
    public void setRoomKey(String roomKey) { this.roomKey = roomKey; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public String getReadyDate() { return readyDate; }
    public void setReadyDate(String readyDate) { this.readyDate = readyDate; }

    public List<Item> getRanks() { return ranks; }
    public void setRanks(List<Item> ranks) { this.ranks = ranks; }
}
