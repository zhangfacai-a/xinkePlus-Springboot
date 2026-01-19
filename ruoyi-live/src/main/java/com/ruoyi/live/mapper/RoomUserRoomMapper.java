package com.ruoyi.live.mapper;

import com.ruoyi.live.domain.RoomUserRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomUserRoomMapper {
    void upsert(RoomUserRoom room);

    RoomUserRoom selectByRoomKey(@Param("roomKey") String roomKey);

    /** 直播间分页列表（分页由 BaseController.startPage() 驱动） */
    List<RoomUserRoom> selectRoomList(@Param("shopName") String shopName, @Param("roomKey") String roomKey);
}
