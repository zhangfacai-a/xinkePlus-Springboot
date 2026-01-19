package com.ruoyi.live.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LiveUserCommentExportResp {

    @Excel(name = "直播间")
    private String roomName;

    @Excel(name = "用户名")
    private String username;

    @Excel(name = "评论内容")
    private String content;

    @Excel(name = "评论时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime commentTime;

    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public java.time.LocalDateTime getCommentTime() {
        return commentTime;
    }
    public void setCommentTime(java.time.LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }
}
