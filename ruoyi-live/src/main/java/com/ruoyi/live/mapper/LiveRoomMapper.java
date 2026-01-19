package com.ruoyi.live.mapper;

import com.ruoyi.live.domain.LiveRoom;
import com.ruoyi.live.dto.LiveRoomPageReq;
import com.ruoyi.live.mapper.vo.LiveRoomPageResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LiveRoomMapper {

    LiveRoom selectByName(@Param("name") String name);

    int insert(LiveRoom room);

    List<LiveRoomPageResp> selectRoomPage(LiveRoomPageReq req);

    /** 导出用：查询符合首页筛选条件的直播间ID列表 */
    List<Long> selectRoomIdsByFilter(@Param("req") LiveRoomPageReq req);
}
