package com.ruoyi.live.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LiveCommentReportReq {

    @NotBlank(message = "roomName不能为空")
    private String roomName;

    @NotBlank(message = "username不能为空")
    private String username;

    @NotBlank(message = "content不能为空")
    private String content;

    /** 可选：上游如果带评论时间就用它，否则服务端用当前时间 */
    private LocalDateTime commentTime;
}
