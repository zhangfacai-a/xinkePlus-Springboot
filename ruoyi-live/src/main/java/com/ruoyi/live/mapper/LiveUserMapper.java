package com.ruoyi.live.mapper;

import com.ruoyi.live.domain.LiveUser;
import com.ruoyi.live.dto.LiveUserPageReq;
import com.ruoyi.live.mapper.vo.LiveUserPageResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LiveUserMapper {

    LiveUser selectByRoomIdAndUsername(@Param("roomId") Long roomId, @Param("username") String username);

    int insert(LiveUser user);

    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    List<LiveUserPageResp> selectUserPage(@Param("req") LiveUserPageReq req);

    /** 导出A：按直播间ID列表导出“每行=用户”的业务视图 */
    List<com.ruoyi.live.excel.LiveRoomUserExportResp> selectExportRoomUsers(@Param("roomIds") List<Long> roomIds);

    /** 导出B：按筛选条件导出“每行=评论”的业务视图（join comment） */
    List<com.ruoyi.live.excel.LiveUserCommentExportResp> selectExportRoomUserComments(@Param("req") LiveUserPageReq req);
}
