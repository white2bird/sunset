package com.sunset.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunset.entity.User.FollowComm;
import com.sunset.entity.Trends.*;
import com.sunset.entity.User.UserFollow;
import com.sunset.entity.User.UserInfoEntity;
import com.sunset.mapper.TrendsMapper;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.TokenUtils;
import com.vdurmont.emoji.EmojiParser;
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
        userFollows.setDescription(EmojiParser.parseToUnicode(uinfo.getDescription()));
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
        if (pubTrends.getImages() != null) {
            String image = JSON.toJSONString(pubTrends.getImages());
            newTrends.setImages(image);
        }
        newTrends.setText(EmojiParser.parseToAliases(pubTrends.getText()));

        String uuid = UUID.randomUUID().toString().toUpperCase();
        newTrends.setId(uuid);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        newTrends.setCreate_time(dateTime);
        trendsMapper.SetTrends(newTrends);
        return ReturnJson.success(null, "ok");
    }

    // 获取用户动态列表
    public ReturnJson<ListTrends> getTrendslist(PageRends pageRends, HttpServletRequest request) {
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        // 时间倒序
        String orby = "create_time desc";
        // 分页查询
        PageHelper.startPage(pageRends.getPage_num(), pageRends.getPage_rows(), orby);
        List<NewTrends> list = trendsMapper.GetTrends(pageRends);

        PageInfo<NewTrends> pageInfo = new PageInfo<>(list);
        List<NewTrends> lists = pageInfo.getList();
        List<ObjTrends> newList = new ArrayList<>();
        lists.forEach((x) -> {
            // 用户信息
            UserInfoEntity uinfo = signMapper.GetUserInfo(x.getUid());
            // 评论列表
            PageHelper.startPage(1, 3, "create_time desc");
            List<CommTrends> commList = trendsMapper.GetTrendsComm(x.getId());
            PageInfo<CommTrends> comm_page = new PageInfo<>(commList);
            ObjTrends objTrends = new ObjTrends();
            // 判断动态是否有点赞
            if (uid != null) {
                String isStar = trendsMapper.FindIsStar(x.getId(), uid);
                if (isStar != null) {
                    objTrends.setIsstar(true);
                } else {
                    objTrends.setIsstar(false);
                }
            } else {
                objTrends.setIsstar(false);
            }
            JSONArray images = JSONArray.parseArray(x.getImages());
            objTrends.setId(x.getId());
            objTrends.setUid(x.getUid());
            // 解码数据库存储的 Emoji 表情符号
            objTrends.setText(EmojiParser.parseToUnicode(x.getText()));
            objTrends.setImages(images);
            objTrends.setStar(x.getStar());
            objTrends.setCreate_time(x.getCreate_time());
            objTrends.setComment_num(comm_page.getTotal());
            // 用户信息
            objTrends.setAvator(uinfo.getAvator());
            objTrends.setNickname(uinfo.getNickname());
            List<CommTrends> comm_list = comm_page.getList();

            List<CommThree> newComm_list = new ArrayList<>();
            // 这里挂载前三条最新的评论
            comm_list.forEach((y) -> {
                // 挂载用户信息
                UserInfoEntity ucomInfo = signMapper.GetUserInfo(y.getUid());
                CommThree commThree = new CommThree();
                commThree.setNickname(ucomInfo.getNickname());
                // 解码数据库存储的 Emoji 表情符号
                commThree.setComment(EmojiParser.parseToUnicode(y.getContent()));
                newComm_list.add(commThree);
            });
            objTrends.setComment_list(newComm_list);


            // 序列化处理完新加旧往新数组追加
            newList.add(objTrends);
        });
        ListTrends listTrends = new ListTrends();
        listTrends.setTotal(pageInfo.getTotal());
        listTrends.setList(newList);
        return ReturnJson.success(listTrends, "ok");
    }

    // 含有图片的动态
    public ReturnJson<ListTrends> getImgTrendsList(PageRends pageRends) {
        // 时间倒序
        String orby = "create_time desc";
        // 分页查询
        PageHelper.startPage(pageRends.getPage_num(), pageRends.getPage_rows(), orby);
        List<NewTrends> list = trendsMapper.GetImgTrends(pageRends);

        PageInfo<NewTrends> pageInfo = new PageInfo<>(list);
        List<NewTrends> lists = pageInfo.getList();
        List<ObjTrends> newList = new ArrayList<>();
        lists.forEach((x) -> {
            // 用户信息
            UserInfoEntity uinfo = signMapper.GetUserInfo(x.getUid());
            ObjTrends objTrends = new ObjTrends();
            JSONArray images = JSONArray.parseArray(x.getImages());
            objTrends.setId(x.getId());
            objTrends.setUid(x.getUid());
            // 解码数据库存储的 Emoji 表情符号
            objTrends.setText(EmojiParser.parseToUnicode(x.getText()));
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
        // 解码数据库存储的 Emoji 表情符号
        objTrends.setText(EmojiParser.parseToUnicode(newTrends.getText()));
        objTrends.setImages(images);
        objTrends.setStar(newTrends.getStar());
        objTrends.setCreate_time(newTrends.getCreate_time());

        return ReturnJson.success(objTrends, "ok");
    }

    // 发表评论
    public ReturnJson<String> setTrendsComm(SetComm setComm, HttpServletRequest request) {
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        NewTrends newTrends = trendsMapper.GetTrensDetail(setComm.getTrends_id());
        if (newTrends == null) {
            return ReturnJson.fail(-1, "该动态不存在");
        }
        CommTrends commTrends = new CommTrends();
        String uuid = UUID.randomUUID().toString().toUpperCase();
        commTrends.setId(uuid);
        commTrends.setUid(uid);
        commTrends.setTrends_id(setComm.getTrends_id());
        // 解码数据库存储的 Emoji 表情符号
        commTrends.setContent(EmojiParser.parseToAliases(setComm.getContent()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        commTrends.setCreate_time(dateTime);
        trendsMapper.SetTrendsComm(commTrends);
        return ReturnJson.success(null, "ok");
    }

    // 根据动态id获取评论列表
    public ReturnJson<ListComment> getTrendsComm(PageComm pageComm) {

        NewTrends newTrends = trendsMapper.GetTrensDetail(pageComm.getTrends_id());
        if (newTrends == null) {
            return ReturnJson.fail(-1, "该动态不存在");
        }
        String id = pageComm.getTrends_id();
        // 时间倒序
        String orby = "create_time desc";
        // 分页查询
        PageHelper.startPage(pageComm.getPage_num(), pageComm.getPage_rows(), orby);
        List<CommTrends> list = trendsMapper.GetTrendsComm(id);
        PageInfo<CommTrends> pageInfo = new PageInfo<>(list);
        List<CommTrends> lists = pageInfo.getList();
        List<CommTrends> newList = new ArrayList<>();
        lists.forEach((x) -> {
            CommTrends commTrends = new CommTrends();
            UserInfoEntity uinfo = signMapper.GetUserInfo(x.getUid());
            commTrends.setId(x.getId());
            commTrends.setTrends_id(x.getTrends_id());
            commTrends.setUid(x.getUid());
            // 解码数据库存储的 Emoji 表情符号
            commTrends.setContent(EmojiParser.parseToUnicode(x.getContent()));
            commTrends.setStar(x.getStar());
            commTrends.setAvator(uinfo.getAvator());
            commTrends.setNickname(uinfo.getNickname());
            commTrends.setCreate_time(x.getCreate_time());
            newList.add(commTrends);
        });

        ListComment listComment = new ListComment();
        listComment.setTotal(pageInfo.getTotal());
        listComment.setList(newList);

        return ReturnJson.success(listComment, "ok");
    }

    // 动态点赞
    public ReturnJson<String> setTrendsStar(String id, HttpServletRequest request) {
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        String followComm_id = trendsMapper.FindIsStar(id, uid);

        NewTrends newTrends = trendsMapper.GetTrensDetail(id);
        int star = newTrends.getStar() == null ? 0 : Integer.parseInt(newTrends.getStar());
        String userUid = trendsMapper.FindUserFollow(newTrends.getUid());
        log.info(userUid);
        if (followComm_id != null) {

            trendsMapper.DeleteStar(followComm_id);
            // 动态列表 star 减一
            star = star - 1;
            if (userUid != null) {
                log.info(String.valueOf(star));
                trendsMapper.UpdateUserStar(star + "", userUid);
            }
            trendsMapper.UpdateTrendsStar(star + "", id);
            return ReturnJson.success("取消点赞ok", "ok");
        }
        FollowComm fcomm = new FollowComm();
        String uuid = UUID.randomUUID().toString().toUpperCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        fcomm.setId(uuid);
        fcomm.setTrends_id(id);
        fcomm.setUid(uid);
        fcomm.setCreate_time(dateTime);
        fcomm.setType(0);
        // 动态列表 star 增一
        star = star + 1;
        if (userUid != null) {
            log.info(String.valueOf(star));
            trendsMapper.UpdateUserStar(star + "", userUid);
        }
        trendsMapper.UpdateTrendsStar(star + "", id);
        trendsMapper.SetTrendsStar(fcomm);
        return ReturnJson.success("点赞ok", "ok");
    }
    // 评论点赞
    public ReturnJson<String> setCommentStar(String comment_id,String trends_id,HttpServletRequest request){
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        String commid = trendsMapper.FindCommentStar(trends_id,comment_id, uid);

        CommTrends trendsComm = trendsMapper.GetCommentDetail(comment_id);
        log.info(String.valueOf(trendsComm.getStar()));
        int star = trendsComm.getStar() == null ? 0 : Integer.parseInt(trendsComm.getStar());
        if (commid != null) {

            trendsMapper.DeleteCommentStar(commid);
            // 动态列表 star 减一
            star = star - 1;
            trendsMapper.UpdateCommentStar(star + "", commid);
            return ReturnJson.success("取消评论点赞ok", "ok");
        }
        FollowComm fcomm = new FollowComm();
        String uuid = UUID.randomUUID().toString().toUpperCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        fcomm.setId(uuid);
        fcomm.setTrends_id(trends_id);
        fcomm.setUid(uid);
        fcomm.setCreate_time(dateTime);
        fcomm.setComment_id(comment_id);
        fcomm.setType(0);
        // 动态列表 star 增一
        star = star + 1;
        trendsMapper.UpdateCommentStar(star + "", commid);
        trendsMapper.SetTrendsStar(fcomm);
        return ReturnJson.success("点赞评论ok", "ok");
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
