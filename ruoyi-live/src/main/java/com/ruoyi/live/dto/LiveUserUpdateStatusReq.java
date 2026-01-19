package com.ruoyi.live.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LiveUserUpdateStatusReq {

    @NotNull(message = "userId不能为空")
    private Long userId;

    @NotNull(message = "status不能为空")
    private Integer status;
}
