package com.sunset.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunset.entity.Trends.*;
import com.sunset.entity.User.UserFollow;
import com.sunset.entity.User.UserInfoEntity;
import com.sunset.mapper.TrendsMapper;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.TokenUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sunset.mapper.SignMapper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class TrendsService {
    @Autowired
    TrendsMapper trendsMapper;
    @Autowired
    SignMapper signMapper;

    // 用户的关注，粉丝，关注
    public ReturnJson<UserFollows> getUserFollow(String uid) {
        UserFollow userFollow = trendsMapper.GetUserFollw(uid);
        UserFollows userFollows = new UserFollows();
        if (userFollow == null) {
            return ReturnJson.fail(-1, "fail");
        }
        UserInfoEntity uinfo = signMapper.GetUserInfo(uid);
        userFollows.setFollowers(userFollow.getFollowers());
        userFollows.setFollowing(userFollow.getFollowing());
        userFollows.setStar(userFollow.getStar());
        // 用户信息
        userFollows.setNickname(uinfo.getNickname());
        userFollows.setAvator(uinfo.getAvator());
        userFollows.setConstellation(uinfo.getConstellation());
        userFollows.setDescription(uinfo.getDescription());
        userFollows.setSex(uinfo.getSex());
        return ReturnJson.success(userFollows, "ok");
    }

    // 发表动态
    public ReturnJson<String> setTrends(PubTrends pubTrends, HttpServletRequest request) {
        NewTrends newTrends = new NewTrends();
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        log.info("用户id：" + map.get("uid"));
        String uid = map.get("uid");
        String text = pubTrends.getText();
        if (text == null || text == "") {
            return ReturnJson.fail(-1, "发表的内容不能为空");
        }
        newTrends.setUid(uid);
        String image = JSON.toJSONString(pubTrends.getImages());
        newTrends.setImages(image);
        newTrends.setText(pubTrends.getText());

        String uuid = UUID.randomUUID().toString().toUpperCase();
        newTrends.setId(uuid);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        newTrends.setCreate_time(dateTime);
        trendsMapper.SetTrends(newTrends);
        return ReturnJson.success(null, "ok");
    }

    // 获取用户动态列表
    public ReturnJson<ListTrends> getTrendslist(PageRends pageRends) {
        // 时间倒序
        String orby = "create_time desc";
        // 分页查询
        PageHelper.startPage(pageRends.getPage_num(), pageRends.getPage_rows(), orby);
        List<NewTrends> list = trendsMapper.GetTrends(pageRends);

        PageInfo<NewTrends> pageInfo = new PageInfo<>(list);
        List<NewTrends> lists = pageInfo.getList();
        List<ObjTrends> newList = new ArrayList<>();
        lists.forEach((x) -> {
            UserInfoEntity uinfo = signMapper.GetUserInfo(x.getUid());

            ObjTrends objTrends = new ObjTrends();
            JSONArray images = JSONArray.parseArray(x.getImages());
            objTrends.setId(x.getId());
            objTrends.setUid(x.getUid());
            objTrends.setText(x.getText());
            objTrends.setImages(images);
            objTrends.setStar(x.getStar());
            objTrends.setCreate_time(x.getCreate_time());

            // 用户信息
            objTrends.setAvator(uinfo.getAvator());
            objTrends.setNickname(uinfo.getNickname());
            // 序列化处理完新加旧往新数组追加
            newList.add(objTrends);
        });
        ListTrends listTrends = new ListTrends();
        listTrends.setTotal(pageInfo.getTotal());
        listTrends.setList(newList);
        return ReturnJson.success(listTrends, "ok");
    }

    // 动态详情
    public ReturnJson<ObjTrends> getTrendsDetail(String id) {
        NewTrends newTrends = trendsMapper.GetTrensDetail(id);
        ObjTrends objTrends = new ObjTrends();
        String uid = newTrends.getUid();
        UserInfoEntity uinfo = signMapper.GetUserInfo(uid);
        JSONArray images = JSONArray.parseArray(newTrends.getImages());
        objTrends.setId(newTrends.getId());
        objTrends.setNickname(uinfo.getNickname());
        objTrends.setAvator(uinfo.getAvator());
        objTrends.setUid(uinfo.getUid());
        objTrends.setText(newTrends.getText());
        objTrends.setImages(images);
        objTrends.setStar(newTrends.getStar());
        objTrends.setCreate_time(newTrends.getCreate_time());

        return ReturnJson.success(objTrends,"ok");
    }
    // 发表评论
    public ReturnJson<String> setTrendsComm(SetComm setComm,HttpServletRequest request){
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        NewTrends newTrends = trendsMapper.GetTrensDetail(setComm.getTrends_id());
        if(newTrends == null){
            return ReturnJson.fail(-1,"该动态不存在");
        }
        CommTrends commTrends = new CommTrends();
        String uuid = UUID.randomUUID().toString().toUpperCase();
        commTrends.setId(uuid);
        commTrends.setUid(uid);
        commTrends.setTrends_id(setComm.getTrends_id());
        commTrends.setContent(setComm.getContent());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        commTrends.setCreate_time(dateTime);
        trendsMapper.SetTrendsComm(commTrends);
        return ReturnJson.success(null,"ok");
    }
    // 根据动态id获取评论列表
    public ReturnJson<ListComment> getTrendsComm(PageComm pageComm){

        NewTrends newTrends = trendsMapper.GetTrensDetail(pageComm.getTrends_id());
        if(newTrends == null){
            return ReturnJson.fail(-1,"该动态不存在");
        }
        String id = pageComm.getTrends_id();
        // 时间倒序
        String orby = "create_time desc";
        // 分页查询
        PageHelper.startPage(pageComm.getPage_num(), pageComm.getPage_rows(), orby);
        List<CommTrends> list =  trendsMapper.GetTrendsComm(id);
        PageInfo<CommTrends> pageInfo = new PageInfo<>(list);
        List<CommTrends> lists = pageInfo.getList();
        List<CommTrends> newList = new ArrayList<>();
        lists.forEach((x) -> {
            CommTrends commTrends = new CommTrends();
            UserInfoEntity uinfo = signMapper.GetUserInfo(x.getUid());
            commTrends.setId(x.getId());
            commTrends.setTrends_id(x.getTrends_id());
            commTrends.setUid(x.getUid());
            commTrends.setContent(x.getContent());
            commTrends.setStar(x.getStar());
            commTrends.setAvator(uinfo.getAvator());
            commTrends.setNickname(uinfo.getNickname());
            commTrends.setCreate_time(x.getCreate_time());
            newList.add(commTrends);
        });

        ListComment listComment = new ListComment();
        listComment.setTotal(pageInfo.getTotal());
        listComment.setList(newList);

        return ReturnJson.success(listComment,"ok");
    }
    // 用于返回的用户关注，粉丝，获赞的新实体类
    @Data
    public static class UserFollows {
        private String uid;
        @Schema(description = "昵称")
        private String nickname;
        @Schema(description = "头像")
        private String avator;
        @Schema(description = "性别")
        private Integer sex;
        @Schema(description = "简介")
        private String description;
        @Schema(description = "星座")
        private String constellation;
        @Schema(description = "关注")
        private String following = "0";
        @Schema(description = "粉丝")
        private String followers = "0";
        @Schema(description = "获赞")
        private String star = "0";
    }
}
