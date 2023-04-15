package com.sunset.controller;

import com.sunset.entity.Trends.*;
import com.sunset.entity.User.FollowAll;
import com.sunset.entity.User.FollowPage;
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
    public ReturnJson<TrendsService.UserFollows> getUserFollow(@RequestParam(name = "uid") String uid, HttpServletRequest request) {
        return trendsService.getUserFollow(uid,request);
    }
    @Operation(summary = "发表动态")
    @PostMapping("/pub_trends")
    @AuthMsToken
    public ReturnJson<String> setTrends(@RequestBody PubTrends pubTrends, HttpServletRequest request) {
        return trendsService.setTrends(pubTrends,request);
    }
    @Operation(summary = "删除动态")
    @PostMapping("/del_trends")
    @AuthMsToken
    public ReturnJson<String> deleteTrends(@RequestParam(name = "id") @Parameter(description="动态id") String id, HttpServletRequest request) {
        return trendsService.deleteTrends(id,request);
    }
    @Operation(summary = "获取动态列表")
    @GetMapping("/get/list")
    public ReturnJson<ListTrends> getTrendslist(@RequestParam(name = "uid", required = false) @Parameter(description="用户id") String uid, @Parameter(description="页码") Integer page_num, @Parameter(description="页数") Integer page_rows,HttpServletRequest request) {
        PageRends p =new PageRends();
        p.setUid(uid);
        p.setPage_num(page_num);
        p.setPage_rows(page_rows);
        return trendsService.getTrendslist(p,request);
    }
    @Operation(summary = "获取动态列表【含有图片】")
    @GetMapping("/home/get")
    public ReturnJson<ListTrends> getTrendslist(@Parameter(description="页码") Integer page_num, @Parameter(description="页数") Integer page_rows) {
        PageRends p =new PageRends();
        p.setPage_num(page_num);
        p.setPage_rows(page_rows);
        return trendsService.getImgTrendsList(p);
    }
    @Operation(summary = "获取动态详情")
    @GetMapping("/get/detail")
    public ReturnJson<ObjTrends> getTrendsDetail(@RequestParam(name = "trends_id") @Parameter(description="动态详情id") String trends_id,HttpServletRequest request) {
        return trendsService.getTrendsDetail(trends_id,request);
    }
    @Operation(summary = "发表评论")
    @PostMapping("/pub/comment")
    @AuthMsToken
    public ReturnJson<String> setTrendsComm(@RequestBody SetComm setComm,HttpServletRequest request) {
        return trendsService.setTrendsComm(setComm,request);
    }
    @Operation(summary = "删除评论")
    @PostMapping("/del/comment")
    @AuthMsToken
    public ReturnJson<String> deleteComm(@RequestParam(name = "id") @Parameter(description="评论id") String id, HttpServletRequest request) {
        return trendsService.deleteComm(id,request);
    }
    @Operation(summary = "根据动态id获取评论列表")
    @GetMapping("/get/comment")
    public ReturnJson<ListComment> getTrendsComm(@RequestParam(name = "trends_id", required = false) @Parameter(description="动态内容id") String trends_id, @Parameter(description="页码") Integer page_num, @Parameter(description="页数") Integer page_rows,HttpServletRequest request) {
        PageComm p =new PageComm();
        p.setTrends_id(trends_id);
        p.setPage_num(page_num);
        p.setPage_rows(page_rows);
        return trendsService.getTrendsComm(p,request);
    }
    @Operation(summary = "动态文章点赞<-->取消动态文章点赞")
    @PostMapping("/set_star")
    @AuthMsToken
    public  ReturnJson<String> setTrendsStar(@RequestParam(name = "trends_id", required = false) @Parameter(description="动态内容id") String trends_id,HttpServletRequest request){
        return trendsService.setTrendsStar(trends_id,request);
    }
    @Operation(summary = "评论点赞<-->取消点赞")
    @PostMapping("/comment/set_star")
    @AuthMsToken
    public  ReturnJson<String> setTrendsStar(@RequestParam(name = "comment_id", required = false) @Parameter(description="评论id") String comment_id,@RequestParam(name = "trends_id", required = false) @Parameter(description="动态内容id") String trends_id,HttpServletRequest request){
        return trendsService.setCommentStar(comment_id,trends_id,request);
    }
    @Operation(summary = "关注<-->取消关注")
    @PostMapping("/follow/set")
    @AuthMsToken
    public  ReturnJson<String> setUserFollows(@RequestParam(name = "uid", required = false) @Parameter(description="用户id") String uid,HttpServletRequest request){
        return trendsService.setUserFollows(uid,request);
    }
    @Operation(summary = "关注列表")
    @PostMapping("/follow/list")
    @AuthMsToken
    public  ReturnJson<ListTrends<FollowAll>> getFollowList(@Parameter(description="页码") Integer page_num, @Parameter(description="页数") Integer page_rows, HttpServletRequest request){
        PageRends p =new PageRends();
        p.setPage_num(page_num);
        p.setPage_rows(page_rows);
        return trendsService.getFollowList(p,request);
    }
}
