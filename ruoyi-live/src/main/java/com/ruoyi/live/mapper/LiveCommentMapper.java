package com.ruoyi.live.mapper;

import com.ruoyi.live.domain.LiveComment;
import com.ruoyi.live.mapper.vo.LiveCommentPageResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface LiveCommentMapper {

    int insert(LiveComment comment);

    List<LiveCommentPageResp> selectCommentList(
            @Param("userId") Long userId,
            @Param("content") String content,
            @Param("beginTime") LocalDateTime beginTime,
            @Param("endTime") LocalDateTime endTime
    );
}
