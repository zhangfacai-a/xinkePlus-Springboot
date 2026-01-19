package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.live.dto.LiveRoomPageReq;
import com.ruoyi.live.dto.LiveUserPageReq;
import com.ruoyi.live.dto.LiveUserUpdateStatusReq;
import com.ruoyi.live.excel.LiveRoomUserExportResp;
import com.ruoyi.live.excel.LiveUserCommentExportResp;
import com.ruoyi.live.mapper.LiveRoomMapper;
import com.ruoyi.live.mapper.vo.LiveCommentPageResp;
import com.ruoyi.live.mapper.vo.LiveRoomPageResp;
import com.ruoyi.live.mapper.vo.LiveUserPageResp;
import com.ruoyi.live.service.LiveAdminService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 后台管理端接口（运营使用）
 */
@RestController
@RequestMapping("/admin/live")
public class LiveAdminController extends BaseController {

    @Resource private LiveRoomMapper liveRoomMapper;
    @Resource private LiveAdminService liveAdminService;

    /** 页面1：直播间列表 */
    @GetMapping("/room/page")
    public List<LiveRoomPageResp> roomPage(LiveRoomPageReq req) {
        return liveRoomMapper.selectRoomPage(req);
    }

    /** 页面1：导出（每行=用户） */
    @GetMapping("/room/export-users")
    public void exportRoomUsers(LiveRoomPageReq req, HttpServletResponse response) {
        List<LiveRoomUserExportResp> list = liveAdminService.exportRoomUsers(req);
        ExcelUtil<LiveRoomUserExportResp> util = new ExcelUtil<>(LiveRoomUserExportResp.class);
        util.exportExcel(response, list, "直播间用户");
    }

    /** 页面2：直播间详情/用户列表 */
    @GetMapping("/user/page")
    public TableDataInfo userPage(@Valid LiveUserPageReq req) {
        startPage(); // ✅ 从 request 里拿 pageNum/pageSize
        List<LiveUserPageResp> list = liveAdminService.userPage(req);
        return getDataTable(list);
    }

    /** 页面2：修改用户状态（核心操作） */
    @PutMapping("/user/status")
    public Object updateUserStatus(@Valid @RequestBody LiveUserUpdateStatusReq req) {
        liveAdminService.updateUserStatus(req.getUserId(), req.getStatus());
        return Map.of("code", 200, "msg", "ok");
    }

    /** 页面2：导出（每行=评论） */
    @GetMapping("/user/export-comments")
    public void exportRoomUserComments(@Valid LiveUserPageReq req, HttpServletResponse response) {
        List<LiveUserCommentExportResp> list = liveAdminService.exportRoomUserComments(req);

        // ✅ 关键：加一行日志确认真的有数据
        System.out.println("export-comments list.size = " + (list == null ? 0 : list.size()));

        ExcelUtil<LiveUserCommentExportResp> util = new ExcelUtil<>(LiveUserCommentExportResp.class);
        util.exportExcel(response, list, "直播间评论");
    }
    /** 页面3：用户评论弹窗/页面（只读） */
    @GetMapping("/comment/list")
    public List<LiveCommentPageResp> commentList(@RequestParam Long userId,
                                                 @RequestParam(required = false) String content,
                                                 @RequestParam(required = false) LocalDateTime beginTime,
                                                 @RequestParam(required = false) LocalDateTime endTime) {
        return liveAdminService.commentList(userId, content, beginTime, endTime);
    }
}
