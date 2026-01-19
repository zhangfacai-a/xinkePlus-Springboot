package com.ruoyi.live.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.live.dto.RoomUserAudienceQuery;
import com.ruoyi.live.dto.RoomUserBizUpdateReq;
import com.ruoyi.live.mapper.RoomUserRelationMapper;
import com.ruoyi.live.service.IRoomUserManageService;
import com.ruoyi.live.vo.RoomUserAudienceDetailVO;
import com.ruoyi.live.vo.RoomUserAudienceVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RoomUserManageServiceImpl implements IRoomUserManageService {

    private final RoomUserRelationMapper relationMapper;

    public RoomUserManageServiceImpl(RoomUserRelationMapper relationMapper) {
        this.relationMapper = relationMapper;
    }

    @Override
    public List<RoomUserAudienceVO> listRoomUsers(Long roomId, RoomUserAudienceQuery q) {
        return relationMapper.selectAudienceList(roomId, q);
    }

    @Override
    public RoomUserAudienceDetailVO getRoomUserDetail(Long roomId, Long userId) {
        return relationMapper.selectAudienceDetail(roomId, userId);
    }

    @Override
    public void updateBiz(RoomUserBizUpdateReq req) {
        // 【备注】业务校验：已下单必须填订单号
        if (req.getFollowStatus() != null && req.getFollowStatus() == 2) {
            if (!StringUtils.hasText(req.getOrderNo())) {
                throw new ServiceException("已下单状态必须填写订单号");
            }
        }

        // 【备注】非已下单状态：建议清空订单号，避免脏数据
        if (req.getFollowStatus() == null || req.getFollowStatus() != 2) {
            req.setOrderNo(null);
        }

        int rows = relationMapper.updateBizFields(req);
        if (rows <= 0) {
            throw new ServiceException("更新失败：未找到该直播间用户关系记录");
        }
    }
}
