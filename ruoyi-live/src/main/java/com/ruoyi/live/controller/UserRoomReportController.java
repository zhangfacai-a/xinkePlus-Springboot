package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.live.dto.UserRoomReportReq;
import com.ruoyi.live.service.IUserRoomReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/live/userRoom")
public class UserRoomReportController extends BaseController {

    private final IUserRoomReportService reportService;

    public UserRoomReportController(IUserRoomReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/report")
    public AjaxResult report(@RequestBody UserRoomReportReq req) {
        reportService.report(req);
        return success();
    }
}
