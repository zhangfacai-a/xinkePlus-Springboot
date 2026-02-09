package com.ruoyi.live.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.live.dto.UserRoomStatQuery;
import com.ruoyi.live.service.IUserRoomStatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/live/userRoom/stat")
public class UserRoomStatController extends BaseController {

    private final IUserRoomStatService statService;

    public UserRoomStatController(IUserRoomStatService statService) {
        this.statService = statService;
    }

    @GetMapping("/summary")
    public AjaxResult summary(UserRoomStatQuery q) {
        return success(statService.summary(q));
    }

    @GetMapping("/monthly")
    public AjaxResult monthly(UserRoomStatQuery q) {
        return success(statService.monthly(q));
    }
}
