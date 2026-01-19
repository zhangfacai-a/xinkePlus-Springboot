package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.live.dto.RoomUserAudienceQuery;
import com.ruoyi.live.dto.RoomUserBizUpdateReq;
import com.ruoyi.live.service.IRoomUserManageService;
import com.ruoyi.live.vo.RoomUserAudienceDetailVO;
import com.ruoyi.live.vo.RoomUserAudienceVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播间用户管理（系统用）
 *
 * 【备注】
 * - 这里只处理“人工维护字段”的增改查（负责人/状态/订单号/备注）
 * - 不参与插件上报采集逻辑
 */
@RestController
@RequestMapping("/live/roomUserManage")
public class RoomUserManageController extends BaseController {

    private final IRoomUserManageService manageService;

    public RoomUserManageController(IRoomUserManageService manageService) {
        this.manageService = manageService;
    }

    /**
     * 直播间下用户列表（分页 + 筛选）
     * GET /live/roomUserManage/room/{roomId}/users?nickname=&secUid=&ownerName=&followStatus=
     */
    @GetMapping("/room/{roomId}/users")
    public TableDataInfo listRoomUsers(@PathVariable Long roomId, RoomUserAudienceQuery q) {
        startPage();
        List<RoomUserAudienceVO> list = manageService.listRoomUsers(roomId, q);
        return getDataTable(list);
    }

    /**
     * 用户详情（直播间维度）
     */
    @GetMapping("/room/{roomId}/user/{userId}")
    public AjaxResult detail(@PathVariable Long roomId, @PathVariable Long userId) {
        RoomUserAudienceDetailVO vo = manageService.getRoomUserDetail(roomId, userId);
        return success(vo);
    }

    /**
     * 更新跟进字段（人工维护）
     * PUT /live/roomUserManage/room/{roomId}/user/{userId}/biz
     */
    @PutMapping("/room/{roomId}/user/{userId}/biz")
    public AjaxResult updateBiz(@PathVariable Long roomId,
                                @PathVariable Long userId,
                                @RequestBody RoomUserBizUpdateReq req) {
        // 【备注】以路径参数为准，避免前端漏传/篡改
        req.setRoomId(roomId);
        req.setUserId(userId);

        manageService.updateBiz(req);
        return success();
    }
}
