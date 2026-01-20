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

    /** 抖音用户主页固定前缀：上报的 secUid 可能会带这个 */
    private static final String DOUYIN_USER_PREFIX = "https://www.douyin.com/user/";

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

            // ===== 关键改动：统一规范化 secUid（去掉 https://www.douyin.com/user/ 前缀等） =====
            String secUid = normalizeSecUid(item.getSecUid());
            if (secUid == null || secUid.isBlank()) {
                continue;
            }
            // 可选：把清洗后的值写回 item，确保后续逻辑不会再用到原始带前缀的值
            item.setSecUid(secUid);

            // 3.1 upsert user（库里只存纯 secUid）
            RoomUserUser user = new RoomUserUser();
            user.setSecUid(secUid);
            user.setNickname(item.getNickname());
            user.setLastSeenTime(now);
            userMapper.upsert(user);

            RoomUserUser dbUser = userMapper.selectBySecUid(secUid);
            if (dbUser == null) {
                throw new IllegalStateException("user upsert 后未查询到记录: " + secUid);
            }
            Long userId = dbUser.getId();

            // 3.2 upsert relation（房间下的用户状态）
            RoomUserRelation rel = new RoomUserRelation();
            rel.setRoomId(roomId);
            rel.setUserId(userId);
            rel.setIsFollower(Boolean.TRUE.equals(item.getIsFollower()));
            rel.setIsFollowing(Boolean.TRUE.equals(item.getIsFollowing()));
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

    /**
     * 将上报的 secUid 统一规范化成“纯 secUid”
     * 支持输入：
     * - MS4wLjABAAAAxxx
     * - https://www.douyin.com/user/MS4wLjABAAAAxxx
     * - https://www.douyin.com/user/MS4wLjABAAAAxxx?modal_id=...
     * - https://www.douyin.com/user/MS4wLjABAAAAxxx/
     */
    private String normalizeSecUid(String input) {
        if (input == null) {
            return null;
        }
        String s = input.trim();
        if (s.isEmpty()) {
            return s;
        }

        // 去掉 query 参数
        int qIndex = s.indexOf('?');
        if (qIndex >= 0) {
            s = s.substring(0, qIndex);
        }

        // 去掉末尾的 /
        while (s.endsWith("/")) {
            s = s.substring(0, s.length() - 1);
        }

        // 去掉固定前缀
        if (s.startsWith(DOUYIN_USER_PREFIX)) {
            s = s.substring(DOUYIN_USER_PREFIX.length());
        }

        // 兜底：如果是其他 http 链接形态，尽量取最后一段
        if (s.startsWith("http")) {
            int lastSlash = s.lastIndexOf('/');
            if (lastSlash >= 0 && lastSlash < s.length() - 1) {
                s = s.substring(lastSlash + 1);
            }
        }

        return s;
    }

    private Date parseReadyDate(String readyDate) {
        if (readyDate == null || readyDate.isBlank()) {
            return new Date();
        }
        try {
            return READY_DATE_FMT.parse(readyDate.trim());
        } catch (ParseException e) {
            throw new IllegalArgumentException("readyDate 格式错误，期望 yyyy.MM.dd，实际：" + readyDate, e);
        }
    }
}
