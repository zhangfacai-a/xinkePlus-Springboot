package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.live.dto.RoomUserReportReq;
import com.ruoyi.live.service.IRoomUserReportService;
import org.springframework.web.bind.annotation.*;

/**
 * 直播间用户数据上报控制器
 *
 * <p>
 * 该 Controller 主要用于接收【直播插件 / 外部系统】上报的直播间用户数据，
 * 本身不包含任何业务处理逻辑，仅作为 HTTP 接口入口。
 * </p>
 *
 * <p>
 * 核心职责：
 * 1. 接收前端或插件通过 HTTP POST 提交的用户数据
 * 2. 将数据交由 Service 层统一处理（入库、更新、统计等）
 * 3. 返回统一格式的 AjaxResult 响应
 * </p>
 *
 * <p>
 * 接口路径前缀：/live/roomUser
 * </p>
 */
@RestController
@RequestMapping("/live/roomUser")
public class RoomUserReportController extends BaseController {

    /**
     * 直播间用户数据上报 Service
     *
     * 具体的业务逻辑（如：
     * - 直播间 upsert
     * - 用户 upsert
     * - 用户与直播间关系维护
     * - 每日排行/统计数据写入
     * ）均在 Service 层完成
     */
    private final IRoomUserReportService reportService;

    public RoomUserReportController(IRoomUserReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * - 直播插件定时/实时上报当前直播间的观众、排行、观看时长等信息
     * 请求方式：POST
     * 请求路径：/live/roomUser/report
     */
    @PostMapping("/report")
    public AjaxResult report(@RequestBody RoomUserReportReq req) {
        reportService.report(req);
        return success();
    }
}
