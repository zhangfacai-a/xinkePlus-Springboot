package com.ruoyi.live.mapper;

import com.ruoyi.live.domain.RoomUserRelation;
import com.ruoyi.live.dto.RoomUserAudienceQuery;
import com.ruoyi.live.dto.RoomUserBizUpdateReq;
import com.ruoyi.live.vo.RoomUserAudienceDetailVO;
import com.ruoyi.live.vo.RoomUserAudienceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomUserRelationMapper {
    void upsert(RoomUserRelation relation);

    /** 直播间下用户列表（分页由 BaseController.startPage() 驱动） */
    List<RoomUserAudienceVO> selectAudienceList(@Param("roomId") Long roomId, @Param("q") RoomUserAudienceQuery q);

    /** 直播间内用户详情 */
    RoomUserAudienceDetailVO selectAudienceDetail(@Param("roomId") Long roomId, @Param("userId") Long userId);

    /** 更新跟进字段（人工维护） */
    int updateBizFields(RoomUserBizUpdateReq req);
}
