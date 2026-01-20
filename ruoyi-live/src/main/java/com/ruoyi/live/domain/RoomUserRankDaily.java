package com.ruoyi.live.domain;

import lombok.Data;

import java.util.Date;
@Data
public class RoomUserRankDaily {
    private Long id;
    private Long roomId;
    private Long userId;
    private Date statDate;       // 用 java.util.Date 存 DATE 也可以（xml 里用 jdbcType=DATE）
    private Integer watchTime;
    private Date capturedTime;

}
