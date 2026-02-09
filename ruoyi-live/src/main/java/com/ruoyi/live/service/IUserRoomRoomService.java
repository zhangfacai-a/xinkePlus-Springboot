package com.ruoyi.live.service;

import com.ruoyi.live.dto.UserRoomRoomQuery;
import com.ruoyi.live.dto.UserRoomRoomUpdateNameReq;
import com.ruoyi.live.vo.UserRoomRoomVO;

import java.util.List;

public interface IUserRoomRoomService {
    List<UserRoomRoomVO> listRooms(UserRoomRoomQuery q);

    void updateRoomName(UserRoomRoomUpdateNameReq req);
}
