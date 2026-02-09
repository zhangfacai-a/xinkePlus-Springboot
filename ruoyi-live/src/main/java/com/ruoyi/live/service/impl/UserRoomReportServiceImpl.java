package com.ruoyi.live.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.live.domain.UserRoomRankDaily;
import com.ruoyi.live.domain.UserRoomRelation;
import com.ruoyi.live.domain.UserRoomUser;
import com.ruoyi.live.domain.UserRoomVisitDaily;
import com.ruoyi.live.dto.UserRoomReportReq;
import com.ruoyi.live.mapper.UserRoomReportMapper;
import com.ruoyi.live.service.IUserRoomReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserRoomReportServiceImpl implements IUserRoomReportService {

    /** 抖音用户主页固定前缀：数据库中 secUid 统一存为这个 URL + 纯 secUid */
    private static final String DOUYIN_USER_PREFIX = "https://www.douyin.com/user/";

    private final UserRoomReportMapper reportMapper;

    public UserRoomReportServiceImpl(UserRoomReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void report(UserRoomReportReq req) {
        if (req == null || req.getRoomKey() == null || req.getRoomKey().isBlank()) {
            throw new ServiceException("roomKey 不能为空");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDate statDate = LocalDate.now(); // ✅ 服务器日期

        // 1) roomKey 必存在：查 room_id，并刷新 last_seen_time（不改 room_name）
        // 1) roomKey 不存在则自动创建；存在则只刷新 last_seen_time（不改 room_name）
        reportMapper.upsertRoomByKey(req.getRoomKey(), now);

// 2) 取 roomId（外键需要 room_id）
        Long roomId = reportMapper.selectRoomIdByRoomKey(req.getRoomKey());
        if (roomId == null) {
            throw new ServiceException("roomKey upsert 后仍未查到：" + req.getRoomKey());
        }

        // 2) ranks 入库
        List<UserRoomReportReq.Item> ranks = req.getRanks();
        if (ranks == null || ranks.isEmpty()) {
            return;
        }

        for (UserRoomReportReq.Item item : ranks) {
            if (item == null) {
                continue;
            }

            // 2.1 secUid 规范化：插件保证纯 secUid，但这里做兜底，防止脏数据
            String secUidPure = normalizeSecUid(item.getSecUid());
            if (secUidPure == null || secUidPure.isBlank()) {
                continue;
            }
            String secUidDb = DOUYIN_USER_PREFIX + secUidPure;

            // 2.2 upsert user（昵称变化视为改名：直接更新 nickname + last_seen_time）
            UserRoomUser user = new UserRoomUser();
            user.setSecUid(secUidDb);
            user.setNickname(item.getNickname());
            user.setLastSeenTime(now);
            reportMapper.upsertUser(user);

            UserRoomUser dbUser = reportMapper.selectUserBySecUid(secUidDb);
            if (dbUser == null || dbUser.getId() == null) {
                throw new ServiceException("user upsert 后未查到：" + secUidDb);
            }
            Long userId = dbUser.getId();

            // 2.3 watchTime：当日累计
            int newCum = item.getWatchTime() == null ? 0 : item.getWatchTime();

            // 2.4 查当天旧累计 oldCum（不存在视为 0）
            Integer oldCumObj = reportMapper.selectDailyWatchTimeCum(roomId, userId, statDate);
            int oldCum = oldCumObj == null ? 0 : oldCumObj;

            // ✅ 增量规则：delta=max(0,newCum-oldCum)，累计回退 delta=0
            int delta = newCum - oldCum;
            if (delta < 0) {
                delta = 0;
            }

            // 2.5 upsert daily：存当日累计（用于后续计算增量）
            UserRoomRankDaily daily = new UserRoomRankDaily();
            daily.setRoomId(roomId);
            daily.setUserId(userId);
            daily.setStatDate(statDate);
            daily.setWatchTimeCum(newCum);
            daily.setCapturedTime(now);
            reportMapper.upsertDaily(daily);

            // 2.6 upsert relation：只更新采集字段 + delta + capture_update_time（不更新人工字段）
            UserRoomRelation rel = new UserRoomRelation();
            rel.setRoomId(roomId);
            rel.setUserId(userId);
            rel.setIsFollower(Boolean.TRUE.equals(item.getIsFollower()));
            rel.setIsFollowing(Boolean.TRUE.equals(item.getIsFollowing()));
            rel.setLastWatchTimeDelta(delta);
            rel.setCaptureUpdateTime(now);
            reportMapper.upsertRelationCapture(rel);

            // 2.7 upsert visit_daily：同日去重，last_visit_time 取最大
            UserRoomVisitDaily visit = new UserRoomVisitDaily();
            visit.setRoomId(roomId);
            visit.setUserId(userId);
            visit.setVisitDate(statDate);
            visit.setLastVisitTime(now);
            reportMapper.upsertVisit(visit);
        }
    }

    /**
     * 将上报的 secUid 统一规范化成“纯 secUid”（不带前缀、不带参数、不带末尾 /）
     * 兜底支持：纯 secUid 或意外传入 URL。
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

        // 如果是固定前缀 URL，截取后半段
        if (s.startsWith(DOUYIN_USER_PREFIX)) {
            s = s.substring(DOUYIN_USER_PREFIX.length());
        }

        // 兜底：其他 http 链接形态，尽量取最后一段
        if (s.startsWith("http")) {
            int lastSlash = s.lastIndexOf('/');
            if (lastSlash >= 0 && lastSlash < s.length() - 1) {
                s = s.substring(lastSlash + 1);
            }
        }

        return s;
    }
}
