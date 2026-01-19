package com.ruoyi.live.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LiveRoomUserExportResp {

    @ExcelProperty("直播间名称")
    private String roomName;

    @ExcelProperty("用户名")
    private String username;

    /** 这里导出中文，避免Excel里出现0/1/2/3 */
    @ExcelProperty("用户状态")
    private String statusText;

    @ExcelProperty("用户首次出现时间")
    private LocalDateTime userCreateTime;

    /** mapper中会映射status字段到这里（临时接收），service再转 statusText */
    private Integer status;
}
