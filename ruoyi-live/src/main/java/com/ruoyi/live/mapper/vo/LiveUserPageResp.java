package com.ruoyi.live.mapper.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LiveUserPageResp {

    private Long id;
    private Long roomId;
    private String username;

    /** 0/1/2/3 */
    private Integer status;

    /** 出现时间 */
    private LocalDateTime createTime;
}
