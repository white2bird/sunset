package com.sunset.controller;

import com.sunset.service.TrendsService;
import com.sunset.utils.AuthMsToken;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sunset.entity.Trends.NewTrends;

@Tag(name = "Trends")
@RestController
@RequestMapping("/trends")
@ResponseBody
@Slf4j
public class TrendsController {
    @Autowired
    TrendsService trendsService;
    @Operation(summary = "获取关注，粉丝，获赞数量")
    @GetMapping("/user/follow")
    public ReturnJson<TrendsService.UserFollows> getUserFollow(@RequestParam(name = "uid") String uid) {
        return trendsService.getUserFollow(uid);
    }
    @Operation(summary = "发表动态")
    @PostMapping("/publish")
    @AuthMsToken
    public ReturnJson<String> pubListTrends(@RequestBody TrendsService.PubTrends pubTrends) {
        return trendsService.setTrends(pubTrends);
    }
}
