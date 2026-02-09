package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.live.dto.UserRoomRoomQuery;
import com.ruoyi.live.dto.UserRoomRoomUpdateNameReq;
import com.ruoyi.live.service.IUserRoomRoomService;
import com.ruoyi.live.vo.UserRoomRoomVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/live/userRoomRoom")
public class UserRoomRoomController extends BaseController {

    private final IUserRoomRoomService roomService;

    public UserRoomRoomController(IUserRoomRoomService roomService) {
        this.roomService = roomService;
    }

    /** 直播间分页（新表 user_room_room） */
    @GetMapping("/list")
    public TableDataInfo list(UserRoomRoomQuery q) {
        startPage();
        List<UserRoomRoomVO> list = roomService.listRooms(q);
        return getDataTable(list);
    }

    /** ✅ 更新直播间名称（后台维护；上报不会覆盖） */
    @PostMapping("/updateName")
    public AjaxResult updateName(@RequestBody UserRoomRoomUpdateNameReq req) {
        roomService.updateRoomName(req);
        return success();
    }
}
