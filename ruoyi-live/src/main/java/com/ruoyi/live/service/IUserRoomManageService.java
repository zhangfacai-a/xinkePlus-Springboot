package com.ruoyi.live.service;

import com.ruoyi.live.dto.UserRoomAudienceQuery;
import com.ruoyi.live.dto.UserRoomBizUpdateReq;
import com.ruoyi.live.vo.UserRoomAudienceDetailVO;
import com.ruoyi.live.vo.UserRoomAudienceVO;
import com.ruoyi.live.vo.UserRoomOwnerHistoryVO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface IUserRoomManageService {

    List<UserRoomAudienceVO> listRoomUsers(UserRoomAudienceQuery q);

    UserRoomAudienceDetailVO getRoomUserDetail(Long roomId, Long userId);
    List<UserRoomOwnerHistoryVO> listOwnerHistory(Long roomId, Long userId);

    void updateBiz(UserRoomBizUpdateReq req);
    void exportRoomUsers(UserRoomAudienceQuery q, HttpServletResponse response);
}
