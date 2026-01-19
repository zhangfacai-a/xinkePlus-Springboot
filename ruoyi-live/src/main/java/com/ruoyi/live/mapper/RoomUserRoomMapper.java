package com.ruoyi.live.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.live.domain.RoomUserRoom;

@Mapper
public interface RoomUserRoomMapper {
    int upsert(RoomUserRoom room);
    RoomUserRoom selectByRoomKey(@Param("roomKey") String roomKey);
}
