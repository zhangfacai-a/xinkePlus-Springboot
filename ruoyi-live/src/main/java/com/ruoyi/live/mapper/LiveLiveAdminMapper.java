package com.ruoyi.live.mapper;

import com.ruoyi.live.dto.*;
import com.ruoyi.live.vo.*;

import java.util.List;

public interface LiveLiveAdminMapper {

    List<LiveLiveRoomPageResp> selectRoomPage(LiveLiveRoomPageReq req);
    List<LiveLiveRoomPageResp> selectRoomExport(LiveLiveRoomPageReq req);

    List<LiveLiveRoomSessionPageResp> selectRoomSessionPage(LiveLiveRoomSessionPageReq req);

    List<LiveLiveSessionReportPageResp> selectSessionReportPage(LiveLiveSessionReportPageReq req);

    List<LiveLiveOwnerPageResp> selectOwnerPage(LiveLiveOwnerPageReq req);
    List<LiveLiveOwnerPageResp> selectOwnerExport(LiveLiveOwnerPageReq req);

    List<LiveLiveOwnerRoomPageResp> selectOwnerRoomPage(LiveLiveOwnerRoomPageReq req);

    List<LiveLiveOwnerRoomReportPageResp> selectOwnerRoomReportPage(LiveLiveOwnerRoomReportPageReq req);
}
