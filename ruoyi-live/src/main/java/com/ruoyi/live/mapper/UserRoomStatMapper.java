package com.ruoyi.live.mapper;

import com.ruoyi.live.vo.UserRoomMonthlyStatRow;
import com.ruoyi.live.vo.UserRoomOwnerStatRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoomStatMapper {

    Long selectRoomIdByRoomKey(@Param("roomKey") String roomKey);

    List<UserRoomOwnerStatRow> statSummaryByOwner(@Param("roomId") Long roomId,
                                                  @Param("beginTime") String beginTime,
                                                  @Param("endTime") String endTime,
                                                  @Param("includeUnassigned") boolean includeUnassigned);

    List<UserRoomMonthlyStatRow> statMonthly(@Param("roomId") Long roomId,
                                             @Param("beginTime") String beginTime,
                                             @Param("endTime") String endTime,
                                             @Param("includeUnassigned") boolean includeUnassigned);
}
