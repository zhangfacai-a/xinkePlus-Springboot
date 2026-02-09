package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.live.dto.UserRoomAudienceQuery;
import com.ruoyi.live.dto.UserRoomBizUpdateReq;
import com.ruoyi.live.service.IUserRoomManageService;
import com.ruoyi.live.vo.UserRoomAudienceDetailVO;
import com.ruoyi.live.vo.UserRoomAudienceVO;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/live/userRoom")
public class UserRoomManageController extends BaseController {

    private final IUserRoomManageService manageService;

    public UserRoomManageController(IUserRoomManageService manageService) {
        this.manageService = manageService;
    }

    /** 列表：支持分页 + 多条件筛选 */
    @GetMapping("/list")
    public TableDataInfo list(UserRoomAudienceQuery q) {
        startPage();
        List<UserRoomAudienceVO> list = manageService.listRoomUsers(q);
        return getDataTable(list);
    }

    /** 详情：含历史 */
    @GetMapping("/detail")
    public AjaxResult detail(@RequestParam Long roomId, @RequestParam Long userId) {
        UserRoomAudienceDetailVO vo = manageService.getRoomUserDetail(roomId, userId);
        return success(vo);
    }

    /** 更新跟进信息：事务 + 24h 负责人规则 + 写历史 */
    @PostMapping("/updateBiz")
    public AjaxResult updateBiz(@RequestBody UserRoomBizUpdateReq req) {
        manageService.updateBiz(req);
        return success();
    }
    @GetMapping("/export")
    public void export(UserRoomAudienceQuery q, HttpServletResponse response) {
        // 注意：这里不要 return AjaxResult，直接写流下载
        manageService.exportRoomUsers(q, response);
    }
    @GetMapping("/ownerHistory")
    public AjaxResult ownerHistory(@RequestParam Long roomId, @RequestParam Long userId) {
        return success(manageService.listOwnerHistory(roomId, userId));
    }



}
