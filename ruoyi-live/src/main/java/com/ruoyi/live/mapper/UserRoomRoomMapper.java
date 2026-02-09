package com.ruoyi.live.mapper;

import com.ruoyi.live.dto.UserRoomRoomQuery;
import com.ruoyi.live.vo.UserRoomRoomVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoomRoomMapper {
    List<UserRoomRoomVO> selectRoomList(@Param("q") UserRoomRoomQuery q);

    int updateRoomNameById(@Param("roomId") Long roomId, @Param("roomName") String roomName);
}
