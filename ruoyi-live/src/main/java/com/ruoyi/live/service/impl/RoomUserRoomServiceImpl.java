package com.ruoyi.live.service.impl;

import com.ruoyi.live.domain.RoomUserRoom;
import com.ruoyi.live.mapper.RoomUserRoomMapper;
import com.ruoyi.live.service.IRoomUserRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomUserRoomServiceImpl implements IRoomUserRoomService {

    private final RoomUserRoomMapper roomMapper;

    public RoomUserRoomServiceImpl(RoomUserRoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Override
    public List<RoomUserRoom> listRooms(String shopName, String roomKey) {
        return roomMapper.selectRoomList(shopName, roomKey);
    }
}
