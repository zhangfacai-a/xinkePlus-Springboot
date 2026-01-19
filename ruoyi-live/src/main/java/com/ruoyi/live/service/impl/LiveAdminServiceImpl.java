package com.ruoyi.live.service.impl;

import com.ruoyi.live.dto.LiveRoomPageReq;
import com.ruoyi.live.dto.LiveUserPageReq;
import com.ruoyi.live.excel.LiveRoomUserExportResp;
import com.ruoyi.live.excel.LiveUserCommentExportResp;
import com.ruoyi.live.mapper.LiveCommentMapper;
import com.ruoyi.live.mapper.LiveRoomMapper;
import com.ruoyi.live.mapper.LiveUserMapper;
import com.ruoyi.live.mapper.vo.LiveCommentPageResp;
import com.ruoyi.live.mapper.vo.LiveUserPageResp;
import com.ruoyi.live.service.LiveAdminService;
import com.ruoyi.live.support.LiveUserStatusEnum;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class LiveAdminServiceImpl implements LiveAdminService {

    @Resource private LiveRoomMapper liveRoomMapper;
    @Resource private LiveUserMapper liveUserMapper;
    @Resource private LiveCommentMapper liveCommentMapper;

    @Override
    public List<LiveUserPageResp> userPage(LiveUserPageReq req) {
        // 你 mapper SQL 里如果已支持 status/username/time，就这里不用再处理
        return liveUserMapper.selectUserPage(req);
    }

    @Override
    public void updateUserStatus(Long userId, Integer status) {
        liveUserMapper.updateStatus(userId, status);
    }

    @Override
    public List<LiveCommentPageResp> commentList(Long userId, String content, LocalDateTime beginTime, LocalDateTime endTime) {
        return liveCommentMapper.selectCommentList(userId, content, beginTime, endTime);
    }

    @Override
    public List<LiveRoomUserExportResp> exportRoomUsers(LiveRoomPageReq req) {
        List<Long> roomIds = liveRoomMapper.selectRoomIdsByFilter(req);
        if (roomIds == null || roomIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<LiveRoomUserExportResp> list = liveUserMapper.selectExportRoomUsers(roomIds);
        for (LiveRoomUserExportResp row : list) {
            row.setStatusText(LiveUserStatusEnum.nameOf(row.getStatus()));
            row.setStatus(null);
        }
        return list;
    }

    @Override
    public List<LiveUserCommentExportResp> exportRoomUserComments(LiveUserPageReq req) {
        List<LiveUserCommentExportResp> list = liveUserMapper.selectExportRoomUserComments(req);
        // 如果你的 LiveUserCommentExportResp 里有 status/statusText，也可以在这里补：
        // for (LiveUserCommentExportResp row : list) { ... }
        return list;
    }
}
