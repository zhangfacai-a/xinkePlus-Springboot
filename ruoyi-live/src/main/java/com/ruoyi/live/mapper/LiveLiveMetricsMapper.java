package com.ruoyi.live.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface LiveLiveMetricsMapper {

    Long selectRoomIdByKey(@Param("roomKey") String roomKey);

    int insertRoom(@Param("roomKey") String roomKey, @Param("roomName") String roomName);

    int updateRoomName(@Param("roomId") Long roomId, @Param("roomName") String roomName);

    Long selectOwnerIdByName(@Param("ownerName") String ownerName);

    int insertOwner(@Param("ownerName") String ownerName);

    Long selectSessionIdByPlatformSessionRoomId(@Param("platformSessionRoomId") String platformSessionRoomId);

    Date selectSessionStartTime(@Param("sessionId") Long sessionId);

    int insertSession(@Param("roomId") Long roomId,
                      @Param("platformSessionRoomId") String platformSessionRoomId,
                      @Param("startTime") Date startTime);

    /** 同session的上一条reportTime（全局，不区分负责人） */
    Date selectLastReportTimeBySession(@Param("sessionId") Long sessionId);

    int insertReport(@Param("sessionId") Long sessionId,
                     @Param("ownerId") Long ownerId,
                     @Param("reportTime") Date reportTime,
                     @Param("lastReportTime") Date lastReportTime,
                     @Param("diffMinutes") Integer diffMinutes,
                     @Param("gmvAmount") BigDecimal gmvAmount,
                     @Param("dealItemCnt") Integer dealItemCnt,
                     @Param("refundAmount") BigDecimal refundAmount,
                     @Param("exposureCnt") Long exposureCnt,
                     @Param("viewerCnt") Long viewerCnt,
                     @Param("newFansCnt") Long newFansCnt,
                     @Param("dealUserCnt") Long dealUserCnt,
                     @Param("qianchuanCost") BigDecimal qianchuanCost,
                     @Param("exposureWatchRate") BigDecimal exposureWatchRate,
                     @Param("watchFollowRate") BigDecimal watchFollowRate,
                     @Param("conversionRate") BigDecimal conversionRate);
}
