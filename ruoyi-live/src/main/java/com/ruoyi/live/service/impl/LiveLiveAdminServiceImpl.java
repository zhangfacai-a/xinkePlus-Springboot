package com.ruoyi.live.service.impl;

import com.ruoyi.live.dto.*;
import com.ruoyi.live.mapper.LiveLiveAdminMapper;
import com.ruoyi.live.service.LiveLiveAdminService;
import com.ruoyi.live.vo.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveLiveAdminServiceImpl implements LiveLiveAdminService {

    @Resource
    private LiveLiveAdminMapper adminMapper;

    @Override
    public List<LiveLiveRoomPageResp> roomPage(LiveLiveRoomPageReq req) {
        return adminMapper.selectRoomPage(req);
    }

    @Override
    public List<LiveLiveRoomPageResp> exportRooms(LiveLiveRoomPageReq req) {
        return adminMapper.selectRoomExport(req);
    }

    @Override
    public List<LiveLiveRoomSessionPageResp> roomSessionPage(LiveLiveRoomSessionPageReq req) {
        return adminMapper.selectRoomSessionPage(req);
    }

    @Override
    public List<LiveLiveSessionReportPageResp> sessionReportPage(LiveLiveSessionReportPageReq req) {
        return adminMapper.selectSessionReportPage(req);
    }

    @Override
    public List<LiveLiveOwnerPageResp> ownerPage(LiveLiveOwnerPageReq req) {
        return adminMapper.selectOwnerPage(req);
    }

    @Override
    public List<LiveLiveOwnerPageResp> exportOwners(LiveLiveOwnerPageReq req) {
        return adminMapper.selectOwnerExport(req);
    }

    @Override
    public List<LiveLiveOwnerRoomPageResp> ownerRoomPage(LiveLiveOwnerRoomPageReq req) {
        return adminMapper.selectOwnerRoomPage(req);
    }

    @Override
    public List<LiveLiveOwnerRoomReportPageResp> ownerRoomReportPage(LiveLiveOwnerRoomReportPageReq req) {
        return adminMapper.selectOwnerRoomReportPage(req);
    }
}
