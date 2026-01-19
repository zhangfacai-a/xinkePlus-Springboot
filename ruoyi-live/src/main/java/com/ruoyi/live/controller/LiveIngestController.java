package com.ruoyi.live.controller;

import com.ruoyi.live.dto.LiveCommentReportReq;
import com.ruoyi.live.service.LiveIngestService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 上报端接口：接收“评论上报”
 * - 首次见到直播间/用户自动创建
 * - 评论只读插入
 */
@RestController
@RequestMapping("/api/live")
public class LiveIngestController {

    @Resource private LiveIngestService liveIngestService;

    @PostMapping("/comment/report")
    public Object report(@Valid @RequestBody LiveCommentReportReq req) {
        liveIngestService.reportComment(req);
        // 返回体按你项目统一返回体（R/AjaxResult）自行替换
        return Map.of("code", 200, "msg", "ok");
    }
}
