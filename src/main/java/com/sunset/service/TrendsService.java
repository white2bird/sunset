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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class TrendsService {
    @Autowired
    TrendsMapper trendsMapper;
    SignMapper signMapper;

    // 用户的关注，粉丝，关注
    public ReturnJson<UserFollows> getUserFollow(String uid) {
        UserFollow userFollow = trendsMapper.GetUserFollw(uid);
        UserFollows userFollows = new UserFollows();
        if (userFollow == null) {
            return ReturnJson.fail(-1, "fail");
        }
        userFollows.setFollowers(userFollow.getFollowers());
        userFollows.setFollowing(userFollow.getFollowing());
        userFollows.setStar(userFollow.getStar());

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

    // 用于返回的用户关注，粉丝，获赞的新实体类
    @Data
    public static class UserFollows {
        private String uid;
        @Schema(description = "关注")
        private String following = "0";
        @Schema(description = "粉丝")
        private String followers = "0";
        @Schema(description = "获赞")
        private String star = "0";
    }
}
