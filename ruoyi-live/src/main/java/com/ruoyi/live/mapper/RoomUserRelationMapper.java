package com.ruoyi.live.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ruoyi.live.domain.RoomUserRelation;

@Mapper
public interface RoomUserRelationMapper {
    int upsert(RoomUserRelation relation);
}
