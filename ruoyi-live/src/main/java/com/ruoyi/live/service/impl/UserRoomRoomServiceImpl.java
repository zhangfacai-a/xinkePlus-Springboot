package com.ruoyi.live.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.live.dto.UserRoomRoomQuery;
import com.ruoyi.live.dto.UserRoomRoomUpdateNameReq;
import com.ruoyi.live.mapper.UserRoomRoomMapper;
import com.ruoyi.live.service.IUserRoomRoomService;
import com.ruoyi.live.vo.UserRoomRoomVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserRoomRoomServiceImpl implements IUserRoomRoomService {

    private final UserRoomRoomMapper roomMapper;

    public UserRoomRoomServiceImpl(UserRoomRoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Override
    public List<UserRoomRoomVO> listRooms(UserRoomRoomQuery q) {
        return roomMapper.selectRoomList(q);
    }

    @Override
    public void updateRoomName(UserRoomRoomUpdateNameReq req) {
        if (req == null || req.getRoomId() == null) {
            throw new ServiceException("roomId 不能为空");
        }
        String name = (req.getRoomName() == null) ? "" : req.getRoomName().trim();
        if (!StringUtils.hasText(name)) {
            throw new ServiceException("直播间名称不能为空");
        }
        if (name.length() > 255) {
            throw new ServiceException("直播间名称长度不能超过255");
        }

        int rows = roomMapper.updateRoomNameById(req.getRoomId(), name);
        if (rows == 0) {
            throw new ServiceException("直播间不存在或未更新（roomId=" + req.getRoomId() + "）");
        }
    }
}
