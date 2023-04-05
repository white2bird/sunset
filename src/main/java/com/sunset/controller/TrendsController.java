package com.sunset.controller;

import com.sunset.entity.Trends.*;
import com.sunset.service.TrendsService;
import com.sunset.utils.AuthMsToken;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
    @PostMapping("/pub/trands")
    @AuthMsToken
    public ReturnJson<String> setTrends(@RequestBody PubTrends pubTrends, HttpServletRequest request) {
        return trendsService.setTrends(pubTrends,request);
    }
    @Operation(summary = "获取动态列表")
    @GetMapping("/get/list")
    public ReturnJson<ListTrends> getTrendslist(@RequestParam(name = "uid", required = false) @Parameter(description="用户id") String uid, @Parameter(description="页码") Integer page_num, @Parameter(description="页数") Integer page_rows) {
        PageRends p =new PageRends();
        p.setUid(uid);
        p.setPage_num(page_num);
        p.setPage_rows(page_rows);
        return trendsService.getTrendslist(p);
    }
    @Operation(summary = "获取动态详情")
    @GetMapping("/get/detail")
    public ReturnJson<ObjTrends> getTrendsDetail(@RequestParam(name = "trends_id") @Parameter(description="动态详情id") String trends_id) {
        return trendsService.getTrendsDetail(trends_id);
    }
    @Operation(summary = "发表评论")
    @PostMapping("/pub/comment")
    @AuthMsToken
    public ReturnJson<String> setTrendsComm(@RequestBody SetComm setComm,HttpServletRequest request) {
        return trendsService.setTrendsComm(setComm,request);
    }
    @Operation(summary = "根据动态id获取评论列表")
    @GetMapping("/get/comment")
    public ReturnJson<ListComment> getTrendsComm(@RequestParam(name = "trends_id", required = false) @Parameter(description="动态内容id") String trends_id, @Parameter(description="页码") Integer page_num, @Parameter(description="页数") Integer page_rows) {
        PageComm p =new PageComm();
        p.setTrends_id(trends_id);
        p.setPage_num(page_num);
        p.setPage_rows(page_rows);
        return trendsService.getTrendsComm(p);
    }
}
