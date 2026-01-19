package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.live.domain.RoomUserRoom;
import com.ruoyi.live.service.IRoomUserRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播间列表（系统入口）
 *
 * 【备注】上报入库会不断更新 room_user_room.last_seen_time
 * 系统这里做分页列表 + 点击进入用户列表
 */
@RestController
@RequestMapping("/live/room")
public class RoomUserRoomController extends BaseController {

    private final IRoomUserRoomService roomService;

    public RoomUserRoomController(IRoomUserRoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/list")
    public TableDataInfo list(@RequestParam(required = false) String shopName,
                              @RequestParam(required = false) String roomKey) {
        startPage();
        List<RoomUserRoom> list = roomService.listRooms(shopName, roomKey);
        return getDataTable(list);
    }
}
