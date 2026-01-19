package com.ruoyi.live.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
@Data
public class LiveUserPageReq {

    @NotNull(message = "roomId不能为空")
    private Long roomId;

    private String username;
    private Integer status;

    /** 用户首次出现时间筛选 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
