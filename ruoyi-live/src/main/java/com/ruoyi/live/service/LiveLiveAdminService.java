package com.ruoyi.live.service;

import com.ruoyi.live.dto.*;
import com.ruoyi.live.vo.*;

import java.util.List;

public interface LiveLiveAdminService {

    List<LiveLiveRoomPageResp> roomPage(LiveLiveRoomPageReq req);
    List<LiveLiveRoomPageResp> exportRooms(LiveLiveRoomPageReq req);

    List<LiveLiveRoomSessionPageResp> roomSessionPage(LiveLiveRoomSessionPageReq req);

    List<LiveLiveSessionReportPageResp> sessionReportPage(LiveLiveSessionReportPageReq req);

    List<LiveLiveOwnerPageResp> ownerPage(LiveLiveOwnerPageReq req);
    List<LiveLiveOwnerPageResp> exportOwners(LiveLiveOwnerPageReq req);

    List<LiveLiveOwnerRoomPageResp> ownerRoomPage(LiveLiveOwnerRoomPageReq req);

    List<LiveLiveOwnerRoomReportPageResp> ownerRoomReportPage(LiveLiveOwnerRoomReportPageReq req);
    List<LiveLiveRoomReportExportResp> exportRoomReports(LiveLiveRoomReportExportReq req);

}
