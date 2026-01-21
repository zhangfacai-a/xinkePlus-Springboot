package com.ruoyi.live.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.live.dto.LiveLiveMetricsReportReq;
import com.ruoyi.live.service.LiveLiveMetricsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/live/metrics")
public class LiveLiveMetricsApiController {

    @Resource
    private LiveLiveMetricsService metricsService;

    @PostMapping("/report")
    public AjaxResult report(@RequestBody LiveLiveMetricsReportReq req) {
        metricsService.report(req);
        return AjaxResult.success("上报成功");
    }
}
