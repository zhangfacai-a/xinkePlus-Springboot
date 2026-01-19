package com.ruoyi.live.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ruoyi.live.domain.RoomUserRankDaily;

@Mapper
public interface RoomUserRankDailyMapper {
    int upsert(RoomUserRankDaily daily);
}
