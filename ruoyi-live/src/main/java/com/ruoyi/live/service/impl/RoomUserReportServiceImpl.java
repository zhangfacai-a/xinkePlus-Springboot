package com.ruoyi.live.service.impl;

import com.ruoyi.live.dto.RoomUserReportReq;
import com.ruoyi.live.domain.RoomUserRankDaily;
import com.ruoyi.live.domain.RoomUserRelation;
import com.ruoyi.live.domain.RoomUserRoom;
import com.ruoyi.live.domain.RoomUserUser;
import com.ruoyi.live.mapper.RoomUserRankDailyMapper;
import com.ruoyi.live.mapper.RoomUserRelationMapper;
import com.ruoyi.live.mapper.RoomUserRoomMapper;
import com.ruoyi.live.mapper.RoomUserUserMapper;
import com.ruoyi.live.service.IRoomUserReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RoomUserReportServiceImpl implements IRoomUserReportService {

    private final RoomUserRoomMapper roomMapper;
    private final RoomUserUserMapper userMapper;
    private final RoomUserRelationMapper relationMapper;
    private final RoomUserRankDailyMapper dailyMapper;

    private static final SimpleDateFormat READY_DATE_FMT = new SimpleDateFormat("yyyy.MM.dd");

    public RoomUserReportServiceImpl(RoomUserRoomMapper roomMapper,
                                     RoomUserUserMapper userMapper,
                                     RoomUserRelationMapper relationMapper,
                                     RoomUserRankDailyMapper dailyMapper) {
        this.roomMapper = roomMapper;
        this.userMapper = userMapper;
        this.relationMapper = relationMapper;
        this.dailyMapper = dailyMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void report(RoomUserReportReq req) {
        if (req == null || req.getRoomKey() == null || req.getRoomKey().isBlank()) {
            throw new IllegalArgumentException("roomKey 不能为空");
        }

        Date now = new Date();

        // 1) upsert room
        RoomUserRoom room = new RoomUserRoom();
        room.setRoomKey(req.getRoomKey());
        room.setShopName(req.getShopName());
        room.setLastSeenTime(now);
        roomMapper.upsert(room);

        RoomUserRoom dbRoom = roomMapper.selectByRoomKey(req.getRoomKey());
        if (dbRoom == null) {
            throw new IllegalStateException("room upsert 后未查询到记录");
        }
        Long roomId = dbRoom.getId();

        // 2) 解析 stat_date
        Date statDate = parseReadyDate(req.getReadyDate());

        // 3) ranks 入库
        List<RoomUserReportReq.Item> ranks = req.getRanks();
        if (ranks == null || ranks.isEmpty()) {
            return;
        }

        for (RoomUserReportReq.Item item : ranks) {
            if (item == null || item.getSecUid() == null || item.getSecUid().isBlank()) {
                continue;
            }

            // 3.1 upsert user
            RoomUserUser user = new RoomUserUser();
            user.setSecUid(item.getSecUid());
            user.setNickname(item.getNickname());
            user.setLastSeenTime(now);
            userMapper.upsert(user);

            RoomUserUser dbUser = userMapper.selectBySecUid(item.getSecUid());
            if (dbUser == null) {
                throw new IllegalStateException("user upsert 后未查询到记录: " + item.getSecUid());
            }
            Long userId = dbUser.getId();

            // 3.2 upsert relation（房间下的用户状态）
            RoomUserRelation rel = new RoomUserRelation();
            rel.setRoomId(roomId);
            rel.setUserId(userId);
            rel.setIsFollower(Boolean.TRUE.equals(item.getIsFollower()) ? 1 : 0);
            rel.setIsFollowing(Boolean.TRUE.equals(item.getIsFollowing()) ? 1 : 0);
            rel.setLastWatchTime(item.getWatchTime() == null ? 0 : item.getWatchTime());
            relationMapper.upsert(rel);

            // 3.3 upsert daily（每日明细）
            RoomUserRankDaily daily = new RoomUserRankDaily();
            daily.setRoomId(roomId);
            daily.setUserId(userId);
            daily.setStatDate(statDate);
            daily.setWatchTime(item.getWatchTime() == null ? 0 : item.getWatchTime());
            daily.setCapturedTime(now);
            dailyMapper.upsert(daily);
        }
    }

    private Date parseReadyDate(String readyDate) {
        if (readyDate == null || readyDate.isBlank()) {
            // 如果插件没传 ready_date，就用当天（只要你表里 stat_date 是 DATE，也能正常落）
            return new Date();
        }
        try {
            return READY_DATE_FMT.parse(readyDate.trim());
        } catch (ParseException e) {
            throw new IllegalArgumentException("readyDate 格式错误，期望 yyyy.MM.dd，实际：" + readyDate, e);
        }
    }
}
