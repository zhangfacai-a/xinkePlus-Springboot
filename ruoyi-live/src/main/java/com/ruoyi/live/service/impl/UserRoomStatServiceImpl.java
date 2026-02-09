package com.ruoyi.live.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.live.dto.UserRoomStatQuery;
import com.ruoyi.live.mapper.UserRoomStatMapper;
import com.ruoyi.live.service.IUserRoomStatService;
import com.ruoyi.live.vo.UserRoomMonthlyStatRow;
import com.ruoyi.live.vo.UserRoomOwnerStatRow;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class UserRoomStatServiceImpl implements IUserRoomStatService {

    private final UserRoomStatMapper statMapper;

    public UserRoomStatServiceImpl(UserRoomStatMapper statMapper) {
        this.statMapper = statMapper;
    }

    @Override
    public Map<String, Object> summary(UserRoomStatQuery q) {
        Long roomId = resolveRoomId(q);

        boolean includeUnassigned = q.getIncludeUnassigned() == null || q.getIncludeUnassigned();

        List<UserRoomOwnerStatRow> rows = statMapper.statSummaryByOwner(
                roomId,
                q.getBeginTime(),
                q.getEndTime(),
                includeUnassigned
        );

        List<String> xAxis = new ArrayList<>();
        List<Long> followUsers = new ArrayList<>();
        List<Long> orderUsers = new ArrayList<>();
        List<BigDecimal> ratePercent = new ArrayList<>();
        List<Long> followActions = new ArrayList<>();

        for (UserRoomOwnerStatRow r : rows) {
            long fu = nvl(r.getFollowUserCount());
            long ou = nvl(r.getOrderUserCount());
            BigDecimal rate = fu == 0 ? BigDecimal.ZERO :
                    BigDecimal.valueOf(ou)
                            .multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(fu), 2, RoundingMode.HALF_UP);

            xAxis.add(r.getOwnerName());
            followUsers.add(fu);
            orderUsers.add(ou);
            ratePercent.add(rate);
            followActions.add(nvl(r.getFollowActionCount()));
        }

        List<Map<String, Object>> series = new ArrayList<>();
        series.add(series("已跟进人数", "bar", followUsers, null));
        series.add(series("已下单人数", "bar", orderUsers, null));
        series.add(series("追单率(%)", "line", ratePercent, 1));
        series.add(series("跟进次数", "bar", followActions, null));

        Map<String, Object> data = new HashMap<>();
        data.put("legend", List.of("已跟进人数", "已下单人数", "追单率(%)", "跟进次数"));
        data.put("xAxis", xAxis);
        data.put("series", series);

        // table：补上 rate/ratePercent 便于前端表格
        List<Map<String, Object>> table = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            UserRoomOwnerStatRow r = rows.get(i);
            long fu = followUsers.get(i);
            long ou = orderUsers.get(i);
            BigDecimal rp = ratePercent.get(i);

            Map<String, Object> tr = new LinkedHashMap<>();
            tr.put("ownerName", r.getOwnerName());
            tr.put("followUserCount", fu);
            tr.put("orderUserCount", ou);
            tr.put("rate", fu == 0 ? 0d : BigDecimal.valueOf(ou).divide(BigDecimal.valueOf(fu), 6, RoundingMode.HALF_UP).doubleValue());
            tr.put("ratePercent", rp.doubleValue());
            tr.put("followActionCount", followActions.get(i));
            table.add(tr);
        }
        data.put("table", table);

        return data;
    }

    @Override
    public Map<String, Object> monthly(UserRoomStatQuery q) {
        Long roomId = resolveRoomId(q);
        boolean includeUnassigned = q.getIncludeUnassigned() == null || q.getIncludeUnassigned();

        List<UserRoomMonthlyStatRow> rows = statMapper.statMonthly(
                roomId,
                q.getBeginTime(),
                q.getEndTime(),
                includeUnassigned
        );

        List<String> xAxis = new ArrayList<>();
        List<Long> followUsers = new ArrayList<>();
        List<Long> orderUsers = new ArrayList<>();
        List<BigDecimal> ratePercent = new ArrayList<>();
        List<Long> followActions = new ArrayList<>();

        for (UserRoomMonthlyStatRow r : rows) {
            long fu = nvl(r.getFollowUserCount());
            long ou = nvl(r.getOrderUserCount());
            BigDecimal rate = fu == 0 ? BigDecimal.ZERO :
                    BigDecimal.valueOf(ou)
                            .multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(fu), 2, RoundingMode.HALF_UP);

            xAxis.add(r.getMonth());
            followUsers.add(fu);
            orderUsers.add(ou);
            ratePercent.add(rate);
            followActions.add(nvl(r.getFollowActionCount()));
        }

        List<Map<String, Object>> series = new ArrayList<>();
        series.add(series("已跟进人数", "line", followUsers, null));
        series.add(series("已下单人数", "line", orderUsers, null));
        series.add(series("追单率(%)", "line", ratePercent, 1));
        series.add(series("跟进次数", "line", followActions, null));

        Map<String, Object> data = new HashMap<>();
        data.put("legend", List.of("已跟进人数", "已下单人数", "追单率(%)", "跟进次数"));
        data.put("xAxis", xAxis);
        data.put("series", series);
        return data;
    }

    private Long resolveRoomId(UserRoomStatQuery q) {
        if (q == null) throw new ServiceException("查询参数不能为空");
        if (q.getRoomId() != null) return q.getRoomId();
        if (StringUtils.hasText(q.getRoomKey())) {
            return statMapper.selectRoomIdByRoomKey(q.getRoomKey());
        }
        return null; // 不传 => 全量
    }

    private long nvl(Long v) {
        return v == null ? 0L : v;
    }

    private Map<String, Object> series(String name, String type, Object data, Integer yAxisIndex) {
        Map<String, Object> s = new LinkedHashMap<>();
        s.put("name", name);
        s.put("type", type);
        s.put("data", data);
        if (yAxisIndex != null) {
            s.put("yAxisIndex", yAxisIndex);
        }
        return s;
    }
}
