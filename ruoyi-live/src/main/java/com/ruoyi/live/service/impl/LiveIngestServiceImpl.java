package com.ruoyi.live.service.impl;

import com.ruoyi.live.domain.LiveComment;
import com.ruoyi.live.domain.LiveRoom;
import com.ruoyi.live.domain.LiveUser;
import com.ruoyi.live.dto.LiveCommentReportReq;
import com.ruoyi.live.mapper.LiveCommentMapper;
import com.ruoyi.live.mapper.LiveRoomMapper;
import com.ruoyi.live.mapper.LiveUserMapper;
import com.ruoyi.live.service.LiveIngestService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class LiveIngestServiceImpl implements LiveIngestService {

    @Resource private LiveRoomMapper liveRoomMapper;
    @Resource private LiveUserMapper liveUserMapper;
    @Resource private LiveCommentMapper liveCommentMapper;

    /**
     * 被动上报：收到一条评论 -> 自动创建直播间/用户（如果不存在）-> 插入评论
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reportComment(LiveCommentReportReq req) {
        // 1) room：按name唯一
        LiveRoom room = liveRoomMapper.selectByName(req.getRoomName());
        if (room == null) {
            try {
                LiveRoom toInsert = new LiveRoom();
                toInsert.setName(req.getRoomName());
                toInsert.setCreateTime(LocalDateTime.now());
                liveRoomMapper.insert(toInsert);
                room = toInsert;
            } catch (DuplicateKeyException e) {
                // 并发下唯一索引冲突，回查即可
                room = liveRoomMapper.selectByName(req.getRoomName());
            }
        }

        // 2) user：同直播间内按 username 唯一
        LiveUser user = liveUserMapper.selectByRoomIdAndUsername(room.getId(), req.getUsername());
        if (user == null) {
            try {
                LiveUser toInsert = new LiveUser();
                toInsert.setRoomId(room.getId());
                toInsert.setUsername(req.getUsername());
                toInsert.setStatus(0);
                toInsert.setCreateTime(LocalDateTime.now());
                liveUserMapper.insert(toInsert);
                user = toInsert;
            } catch (DuplicateKeyException e) {
                user = liveUserMapper.selectByRoomIdAndUsername(room.getId(), req.getUsername());
            }
        }

        // 3) comment：只读插入
        LiveComment c = new LiveComment();
        c.setRoomId(room.getId());
        c.setUserId(user.getId());
        c.setContent(req.getContent());
        c.setCreateTime(req.getCommentTime() != null ? req.getCommentTime() : LocalDateTime.now());
        liveCommentMapper.insert(c);
    }
}
