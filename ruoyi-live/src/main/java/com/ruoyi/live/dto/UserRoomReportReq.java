package com.ruoyi.live.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRoomReportReq {

    /** 直播间唯一标识 */
    private String roomKey;

    /** 顾客排行榜 */
    private List<Item> ranks;

    @Data
    public static class Item {
        /** 当日累计观看时长(秒) */
        private Integer watchTime;
        /** 纯 secUid（插件保证不带前缀；服务端仍做兜底） */
        private String secUid;
        /** 昵称 */
        private String nickname;
        /** 用户关注主播 */
        private Boolean isFollower;
        /** 主播关注用户 */
        private Boolean isFollowing;
    }
}
