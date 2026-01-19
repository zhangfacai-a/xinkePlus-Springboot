package com.ruoyi.live.dto;

/**
 * 跟进信息更新请求
 *
 * 【备注】人工维护字段更新入口，不允许从插件上报接口传入这些字段。
 */
public class RoomUserBizUpdateReq {
    private Long roomId;
    private Long userId;

    private String ownerName;
    private Integer followStatus;
    private String orderNo;
    private String remark;

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public Integer getFollowStatus() { return followStatus; }
    public void setFollowStatus(Integer followStatus) { this.followStatus = followStatus; }

    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
