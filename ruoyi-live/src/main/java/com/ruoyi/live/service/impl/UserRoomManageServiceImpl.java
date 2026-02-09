package com.ruoyi.live.service.impl;

import com.alibaba.excel.EasyExcel;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.live.dto.UserRoomAudienceQuery;
import com.ruoyi.live.dto.UserRoomBizUpdateReq;
import com.ruoyi.live.mapper.UserRoomManageMapper;
import com.ruoyi.live.service.IUserRoomManageService;
import com.ruoyi.live.vo.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserRoomManageServiceImpl implements IUserRoomManageService {

    private final UserRoomManageMapper manageMapper;

    public UserRoomManageServiceImpl(UserRoomManageMapper manageMapper) {
        this.manageMapper = manageMapper;
    }

    @Override
    public List<UserRoomAudienceVO> listRoomUsers(UserRoomAudienceQuery q) {
        if (q == null) {
            throw new ServiceException("查询参数不能为空");
        }

        Long roomId = q.getRoomId();
        if (roomId == null) {
            if (!StringUtils.hasText(q.getRoomKey())) {
                throw new ServiceException("roomId 或 roomKey 必须提供一个");
            }
            roomId = manageMapper.selectRoomIdByRoomKey(q.getRoomKey());
        }
        if (roomId == null) {
            throw new ServiceException("room 不存在");
        }

        return manageMapper.selectAudienceList(roomId, q);
    }

    @Override
    public UserRoomAudienceDetailVO getRoomUserDetail(Long roomId, Long userId) {
        if (roomId == null || userId == null) {
            throw new ServiceException("roomId/userId 不能为空");
        }

        UserRoomAudienceVO base = manageMapper.selectAudienceBase(roomId, userId);
        if (base == null) {
            throw new ServiceException("未找到该直播间用户关系记录");
        }

        List<UserRoomFollowHistoryVO> fh = manageMapper.selectFollowHistory(roomId, userId);
        List<UserRoomOwnerHistoryVO> oh = manageMapper.selectOwnerHistory(roomId, userId);

        UserRoomAudienceDetailVO out = new UserRoomAudienceDetailVO();
        out.setBase(base);
        out.setFollowHistory(fh);
        out.setOwnerHistory(oh);
        return out;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(UserRoomBizUpdateReq req) {
        if (req == null || req.getRoomId() == null || req.getUserId() == null) {
            throw new ServiceException("roomId/userId 不能为空");
        }

        // 订单校验：已下单必须有订单号；其他状态自动清空
        if (req.getFollowStatus() != null && req.getFollowStatus() == 2) {
            if (!StringUtils.hasText(req.getOrderNo())) {
                throw new ServiceException("已下单状态必须填写订单号");
            }
        } else {
            req.setOrderNo(null);
        }

        // 锁行读取旧负责人 + 接手时间
        UserRoomRelationLockRow old = manageMapper.selectRelationForUpdate(req.getRoomId(), req.getUserId());
        if (old == null) {
            throw new ServiceException("未找到该直播间用户关系记录");
        }

        // 禁止清空：旧负责人有值 && 新负责人为空 => 拦截
        if (StringUtils.hasText(old.getOwnerName()) && !StringUtils.hasText(req.getOwnerName())) {
            throw new ServiceException("负责人不允许清空");
        }

        // 是否变更负责人（包含 null -> 非null）
        boolean ownerChanged = !Objects.equals(old.getOwnerName(), req.getOwnerName());

        LocalDateTime now = LocalDateTime.now();

        // 负责人变更 24h 规则（测试阶段可改为 1 分钟）
        if (ownerChanged && old.getOwnerAssignTime() != null) {
            // ✅ 测试：1分钟；上线改回 plusHours(24)
            LocalDateTime limit = old.getOwnerAssignTime().plusMinutes(1);
            if (now.isBefore(limit)) {
                throw new ServiceException("负责人在接手后24小时内不可更改");
            }
        }

        // 更新 relation 人工字段（ownerChanged 时更新 owner_assign_time）
        LocalDateTime ownerAssignTime = ownerChanged ? now : null;
        int rows = manageMapper.updateBizFields(req, now, ownerAssignTime, ownerChanged);
        if (rows <= 0) {
            throw new ServiceException("更新失败：未找到该关系记录");
        }

        // 每次保存都写 FollowHistory（快照）
        manageMapper.insertFollowHistory(
                req.getRoomId(),
                req.getUserId(),
                req.getOwnerName(),
                req.getFollowStatus(),
                req.getOrderNo(),
                req.getRemark(),
                req.getIntentionLevel(),
                now
        );

        // 负责人变化才写 OwnerHistory（包含首次分配：null -> 张三）
        if (ownerChanged) {
            manageMapper.insertOwnerHistory(req.getRoomId(), req.getUserId(), req.getOwnerName(), now);
        }
    }

    @Override
    public void exportRoomUsers(UserRoomAudienceQuery q, HttpServletResponse response) {
        // 复用列表查询（同筛选条件）
        List<UserRoomAudienceVO> list = listRoomUsers(q);

        List<UserRoomAudienceExportRow> rows = new ArrayList<>(list.size());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (UserRoomAudienceVO it : list) {
            UserRoomAudienceExportRow r = new UserRoomAudienceExportRow();
            r.setSecUid(it.getSecUid());
            r.setNickname(it.getNickname());

            r.setIsFollower(Boolean.TRUE.equals(it.getIsFollower()) ? "是" : "否");
            r.setIsFollowing(Boolean.TRUE.equals(it.getIsFollowing()) ? "是" : "否");

            // 你新增的“今日累计”如果也要导出，可以在 ExportRow 里加字段后在此赋值：
            // r.setWatchTimeTodayCum(it.getWatchTimeTodayCum());
            r.setLastWatchTimeDelta(it.getLastWatchTimeDelta());

            r.setOwnerName(it.getOwnerName());
            r.setFollowStatusText(followStatusText(it.getFollowStatus()));
            r.setOrderNo(it.getOrderNo());
            r.setRemark(it.getRemark());
            r.setIntentionLevelText(intentionLevelText(it.getIntentionLevel()));

            r.setVisitCount(it.getVisitCount());
            r.setLastVisitTime(it.getLastVisitTime() == null ? "" : dtf.format(it.getLastVisitTime()));
            r.setOwnerAssignTime(it.getOwnerAssignTime() == null ? "" : dtf.format(it.getOwnerAssignTime()));

            r.setCaptureUpdateTime(it.getCaptureUpdateTime() == null ? "" : dtf.format(it.getCaptureUpdateTime()));
            r.setManageUpdateTime(it.getManageUpdateTime() == null ? "" : dtf.format(it.getManageUpdateTime()));

            rows.add(r);
        }

        String fileName = "直播间用户导出.xlsx";
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String encoded = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + encoded);

            EasyExcel.write(response.getOutputStream(), UserRoomAudienceExportRow.class)
                    .sheet("用户列表")
                    .doWrite(rows);

        } catch (Exception e) {
            throw new ServiceException("导出失败：" + e.getMessage());
        }
    }

    private String followStatusText(Integer v) {
        if (v == null) return "";
        return switch (v) {
            case 0 -> "未跟单";
            case 1 -> "跟单中";
            case 2 -> "已下单";
            case 3 -> "未回复";
            default -> String.valueOf(v);
        };
    }

    private String intentionLevelText(Integer v) {
        if (v == null) return "";
        return switch (v) {
            case 1 -> "重点";
            case 2 -> "中级";
            case 3 -> "初级";
            default -> String.valueOf(v);
        };
    }

    @Override
    public List<UserRoomOwnerHistoryVO> listOwnerHistory(Long roomId, Long userId) {
        if (roomId == null || userId == null) {
            throw new ServiceException("roomId/userId 不能为空");
        }
        return manageMapper.selectOwnerHistory(roomId, userId);
    }

}
