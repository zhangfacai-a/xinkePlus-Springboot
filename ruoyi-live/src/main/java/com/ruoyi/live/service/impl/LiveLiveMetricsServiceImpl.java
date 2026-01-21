package com.ruoyi.live.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.live.dto.LiveLiveMetricsReportReq;
import com.ruoyi.live.mapper.LiveLiveMetricsMapper;
import com.ruoyi.live.service.LiveLiveMetricsService;
import com.ruoyi.live.util.LiveLiveNormalizeUtil;
import com.ruoyi.live.util.LiveLiveTimeParser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class LiveLiveMetricsServiceImpl implements LiveLiveMetricsService {

    @Resource
    private LiveLiveMetricsMapper metricsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void report(LiveLiveMetricsReportReq req) {
        if (req == null) throw new IllegalArgumentException("请求体不能为空");
        if (StringUtils.isBlank(req.getRoomId())) throw new IllegalArgumentException("roomId必填");
        if (StringUtils.isBlank(req.getRoomName())) throw new IllegalArgumentException("roomName必填");
        if (StringUtils.isBlank(req.getOwnerName())) throw new IllegalArgumentException("ownerName必填");
        if (StringUtils.isBlank(req.getStartTime())) throw new IllegalArgumentException("startTime必填");
        if (StringUtils.isBlank(req.getReportTime())) throw new IllegalArgumentException("reportTime必填");

        // parse time
        LocalDateTime startLdt = LiveLiveTimeParser.parse(req.getStartTime());
        LocalDateTime reportLdt = LiveLiveTimeParser.parse(req.getReportTime());
        Date startTime = toDate(startLdt);
        Date reportTime = toDate(reportLdt);

        // normalize room
        String roomKey = LiveLiveNormalizeUtil.normalizeRoomName(req.getRoomName());

        // 1) room
        Long roomDbId = metricsMapper.selectRoomIdByKey(roomKey);
        if (roomDbId == null) {
            try {
                metricsMapper.insertRoom(roomKey, req.getRoomName());
            } catch (Exception ignore) {}
            roomDbId = metricsMapper.selectRoomIdByKey(roomKey);
        }
        // 更新展示名（以最新上报为准）
        metricsMapper.updateRoomName(roomDbId, req.getRoomName());

        // 2) owner
        Long ownerId = metricsMapper.selectOwnerIdByName(req.getOwnerName());
        if (ownerId == null) {
            try {
                metricsMapper.insertOwner(req.getOwnerName());
            } catch (Exception ignore) {}
            ownerId = metricsMapper.selectOwnerIdByName(req.getOwnerName());
        }

        // 3) session（roomId 唯一）
        Long sessionId = metricsMapper.selectSessionIdByPlatformSessionRoomId(req.getRoomId());
        if (sessionId == null) {
            try {
                metricsMapper.insertSession(roomDbId, req.getRoomId(), startTime);
            } catch (Exception ignore) {}
            sessionId = metricsMapper.selectSessionIdByPlatformSessionRoomId(req.getRoomId());
        }

        // 4) lastReportTime：同session全局上一条（不区分负责人）
        Date lastReport = metricsMapper.selectLastReportTimeBySession(sessionId);
        if (lastReport == null) {
            // 用session.start_time（更权威）
            Date dbStart = metricsMapper.selectSessionStartTime(sessionId);
            lastReport = (dbStart != null) ? dbStart : startTime;
        }

        // 5) diffMinutes（允许负数）
        long diffMinutes = Duration.between(toLdt(lastReport), reportLdt).toMinutes();

        // 6) 指标兜底
        BigDecimal gmv = nvl(req.getGmvAmount());
        Integer dealItem = req.getDealItemCnt() == null ? 0 : req.getDealItemCnt();
        BigDecimal refund = nvl(req.getRefundAmount());
        Long exposure = req.getExposureCnt() == null ? 0L : req.getExposureCnt();
        Long viewer = req.getViewerCnt() == null ? 0L : req.getViewerCnt();
        Long newFans = req.getNewFansCnt() == null ? 0L : req.getNewFansCnt();
        Long dealUser = req.getDealUserCnt() == null ? 0L : req.getDealUserCnt();
        BigDecimal qianchuan = nvl(req.getQianchuanCost());

        // conversionRate = 成交人数/累计观看人数
        BigDecimal conversionRate = BigDecimal.ZERO;
        if (viewer != null && viewer > 0 && dealUser != null) {
            conversionRate = new BigDecimal(dealUser).divide(new BigDecimal(viewer), 6, BigDecimal.ROUND_HALF_UP);
        }

        metricsMapper.insertReport(
                sessionId, ownerId,
                reportTime, lastReport, (int) diffMinutes,
                gmv, dealItem, refund,
                exposure, viewer, newFans, dealUser,
                qianchuan,
                req.getExposureWatchRate(), req.getWatchFollowRate(),
                conversionRate
        );
    }

    private static BigDecimal nvl(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }

    private static Date toDate(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static LocalDateTime toLdt(Date d) {
        return LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
    }
}
