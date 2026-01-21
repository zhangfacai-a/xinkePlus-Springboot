package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.live.dto.*;
import com.ruoyi.live.service.LiveLiveAdminService;
import com.ruoyi.live.vo.LiveLiveOwnerPageResp;
import com.ruoyi.live.vo.LiveLiveRoomPageResp;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/liveLive")
public class LiveLiveAdminController extends BaseController {

    @Resource
    private LiveLiveAdminService adminService;

    // ============ 直播间维度 ============

    @GetMapping("/room/page")
    public TableDataInfo roomPage(LiveLiveRoomPageReq req) {
        startPage();
        List<LiveLiveRoomPageResp> list = adminService.roomPage(req);
        return getDataTable(list);
    }

    /** 导出：直播间维度（筛选后全量） */
    @PostMapping("/room/export")
    public void exportRoom(HttpServletResponse response, @RequestBody(required = false) LiveLiveRoomPageReq req) {
        if (req == null) req = new LiveLiveRoomPageReq();
        List<LiveLiveRoomPageResp> list = adminService.exportRooms(req);
        ExcelUtil<LiveLiveRoomPageResp> util = new ExcelUtil<>(LiveLiveRoomPageResp.class);
        util.exportExcel(response, list, "直播间维度数据");
    }

    @GetMapping("/room/session/page")
    public TableDataInfo roomSessionPage(LiveLiveRoomSessionPageReq req) {
        startPage();
        return getDataTable(adminService.roomSessionPage(req));
    }

    @GetMapping("/session/report/page")
    public TableDataInfo sessionReportPage(LiveLiveSessionReportPageReq req) {
        startPage();
        return getDataTable(adminService.sessionReportPage(req));
    }

    // ============ 负责人维度 ============

    @GetMapping("/owner/page")
    public TableDataInfo ownerPage(LiveLiveOwnerPageReq req) {
        startPage();
        return getDataTable(adminService.ownerPage(req));
    }

    /** 导出：负责人维度（筛选后全量） */
    @PostMapping("/owner/export")
    public void exportOwner(HttpServletResponse response, @RequestBody(required = false) LiveLiveOwnerPageReq req) {
        if (req == null) req = new LiveLiveOwnerPageReq();
        List<LiveLiveOwnerPageResp> list = adminService.exportOwners(req);
        ExcelUtil<LiveLiveOwnerPageResp> util = new ExcelUtil<>(LiveLiveOwnerPageResp.class);
        util.exportExcel(response, list, "负责人维度数据");
    }

    @GetMapping("/owner/room/page")
    public TableDataInfo ownerRoomPage(LiveLiveOwnerRoomPageReq req) {
        startPage();
        return getDataTable(adminService.ownerRoomPage(req));
    }

    @GetMapping("/owner/room/report/page")
    public TableDataInfo ownerRoomReportPage(LiveLiveOwnerRoomReportPageReq req) {
        startPage();
        return getDataTable(adminService.ownerRoomReportPage(req));
    }

    @GetMapping("/ping")
    public AjaxResult ping() {
        return AjaxResult.success("ok");
    }
}
