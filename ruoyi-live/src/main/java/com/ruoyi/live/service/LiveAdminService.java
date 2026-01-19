package com.ruoyi.live.service;

import com.ruoyi.live.dto.LiveRoomPageReq;
import com.ruoyi.live.dto.LiveUserPageReq;
import com.ruoyi.live.mapper.vo.LiveCommentPageResp;
import com.ruoyi.live.mapper.vo.LiveRoomPageResp;
import com.ruoyi.live.mapper.vo.LiveUserPageResp;

import java.time.LocalDateTime;
import java.util.List;

public interface LiveAdminService {


    List<LiveUserPageResp> userPage(LiveUserPageReq req);

    void updateUserStatus(Long userId, Integer status);

    List<LiveCommentPageResp> commentList(Long userId, String content,
                                         LocalDateTime beginTime,
                                         LocalDateTime endTime);

    /** 导出A：首页导出（每行=用户） */
    List<com.ruoyi.live.excel.LiveRoomUserExportResp> exportRoomUsers(LiveRoomPageReq req);

    /** 导出B：房间用户页导出（每行=评论） */
    List<com.ruoyi.live.excel.LiveUserCommentExportResp> exportRoomUserComments(LiveUserPageReq req);
}
