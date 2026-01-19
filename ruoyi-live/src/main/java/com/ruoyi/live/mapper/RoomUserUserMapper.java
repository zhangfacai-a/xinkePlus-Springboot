package com.ruoyi.live.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.live.domain.RoomUserUser;

@Mapper
public interface RoomUserUserMapper {
    int upsert(RoomUserUser user);
    RoomUserUser selectBySecUid(@Param("secUid") String secUid);
}
