package com.ruoyi.live.mapper;

import com.ruoyi.live.dto.UserRoomAudienceQuery;
import com.ruoyi.live.dto.UserRoomBizUpdateReq;
import com.ruoyi.live.vo.UserRoomAudienceVO;
import com.ruoyi.live.vo.UserRoomFollowHistoryVO;
import com.ruoyi.live.vo.UserRoomOwnerHistoryVO;
import com.ruoyi.live.vo.UserRoomRelationLockRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserRoomManageMapper {

    Long selectRoomIdByRoomKey(@Param("roomKey") String roomKey);

    List<UserRoomAudienceVO> selectAudienceList(@Param("roomId") Long roomId, @Param("q") UserRoomAudienceQuery q);

    UserRoomAudienceVO selectAudienceBase(@Param("roomId") Long roomId, @Param("userId") Long userId);

    List<UserRoomFollowHistoryVO> selectFollowHistory(@Param("roomId") Long roomId, @Param("userId") Long userId);

    List<UserRoomOwnerHistoryVO> selectOwnerHistory(@Param("roomId") Long roomId, @Param("userId") Long userId);

    /** 更新前锁行读取关系记录（用于 24h 校验） */
    UserRoomRelationLockRow selectRelationForUpdate(@Param("roomId") Long roomId, @Param("userId") Long userId);

    /** 更新人工字段（必要时更新 owner_assign_time） */
    int updateBizFields(@Param("req") UserRoomBizUpdateReq req,
                        @Param("manageUpdateTime") LocalDateTime manageUpdateTime,
                        @Param("ownerAssignTime") LocalDateTime ownerAssignTime,
                        @Param("ownerChanged") boolean ownerChanged);

    int insertFollowHistory(@Param("roomId") Long roomId,
                            @Param("userId") Long userId,
                            @Param("ownerName") String ownerName,
                            @Param("followStatus") Integer followStatus,
                            @Param("orderNo") String orderNo,
                            @Param("remark") String remark,
                            @Param("intentionLevel") Integer intentionLevel,
                            @Param("followTime") LocalDateTime followTime);

    int insertOwnerHistory(@Param("roomId") Long roomId,
                           @Param("userId") Long userId,
                           @Param("ownerName") String ownerName,
                           @Param("assignTime") LocalDateTime assignTime);
}
