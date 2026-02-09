package com.ruoyi.live.service;

import com.ruoyi.live.dto.UserRoomStatQuery;

import java.util.Map;

public interface IUserRoomStatService {

    /** 按负责人汇总（适合柱状图+表格） */
    Map<String, Object> summary(UserRoomStatQuery q);

    /** 按月趋势（适合折线图） */
    Map<String, Object> monthly(UserRoomStatQuery q);
}
