package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.live.dto.RoomUserReportReq;
import com.ruoyi.live.service.IRoomUserReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/live/roomUser")
public class RoomUserReportController extends BaseController {

    private final IRoomUserReportService reportService;

    public RoomUserReportController(IRoomUserReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/report")
    public AjaxResult report(@RequestBody RoomUserReportReq req) {
        reportService.report(req);
        return success();
    }
}
