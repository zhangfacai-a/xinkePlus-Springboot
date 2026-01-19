package com.ruoyi.live.service;

import com.ruoyi.live.dto.RoomUserAudienceQuery;
import com.ruoyi.live.dto.RoomUserBizUpdateReq;
import com.ruoyi.live.vo.RoomUserAudienceDetailVO;
import com.ruoyi.live.vo.RoomUserAudienceVO;

import java.util.List;

public interface IRoomUserManageService {
    List<RoomUserAudienceVO> listRoomUsers(Long roomId, RoomUserAudienceQuery q);

    RoomUserAudienceDetailVO getRoomUserDetail(Long roomId, Long userId);

    void updateBiz(RoomUserBizUpdateReq req);
}
