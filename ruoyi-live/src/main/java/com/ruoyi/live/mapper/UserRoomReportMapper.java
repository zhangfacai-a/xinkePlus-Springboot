package com.ruoyi.live.mapper;

import com.ruoyi.live.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface UserRoomReportMapper {

    // room
    Long selectRoomIdByRoomKey(@Param("roomKey") String roomKey);
    int updateRoomLastSeenTime(@Param("roomId") Long roomId, @Param("lastSeenTime") LocalDateTime lastSeenTime);

    // user
    int upsertUser(UserRoomUser user);
    UserRoomUser selectUserBySecUid(@Param("secUid") String secUid);

    // daily
    Integer selectDailyWatchTimeCum(@Param("roomId") Long roomId,
                                    @Param("userId") Long userId,
                                    @Param("statDate") LocalDate statDate);
    int upsertDaily(UserRoomRankDaily daily);

    // relation (capture only)
    int upsertRelationCapture(UserRoomRelation rel);

    // visit daily
    int upsertVisit(UserRoomVisitDaily visit);
    int upsertRoomByKey(@Param("roomKey") String roomKey,
                        @Param("lastSeenTime") LocalDateTime lastSeenTime);

}
