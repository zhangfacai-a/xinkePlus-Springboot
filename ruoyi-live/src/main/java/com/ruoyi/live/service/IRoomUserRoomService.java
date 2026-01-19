package com.ruoyi.live.service;

import com.ruoyi.live.domain.RoomUserRoom;

import java.util.List;

public interface IRoomUserRoomService {
    List<RoomUserRoom> listRooms(String shopName, String roomKey);
}
