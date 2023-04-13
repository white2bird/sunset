package com.sunset.mapper;

import com.sunset.entity.User.FollowComm;
import com.sunset.entity.Trends.*;
import com.sunset.entity.User.UserFollow;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrendsMapper {
    // 初始化关注，粉丝，获赞
    int SetInitFollow(UserFollow userFollow);
    // 获取用户关注，粉丝，获赞
    UserFollow GetUserFollw(String uid);
    // 发布动态
    int SetTrends(NewTrends newTrends);
    // 动态列表
    List<NewTrends> GetTrends(PageRends pageRends);
    // 含有图片的动态【首页】
    List<NewTrends> GetImgTrends(PageRends pageRends);
    // 动态详情
    NewTrends GetTrensDetail(String id);
    // 发表评论
    int SetTrendsComm(CommTrends commTrends);
    // 根据动态id获取评论列表
    List<CommTrends> GetTrendsComm(String id);
    // 动态点赞
    int SetTrendsStar (FollowComm followComm);
    // 是否点赞
    String FindIsStar(String trends_id, String uid);
    // 删除一条点赞记录
    int DeleteStar(String id);
    // 更新动态详情里的点赞 +1
    int UpdateTrendsStar(Integer star,String id);
    // 查询用户记录表中是否点赞
    String FindUserFollow(String uid);
    //更新用户表点赞
    int UpdateUserStar(Integer star,String uid);

    // 评论点赞详情
    CommTrends GetCommentDetail(String id);
    // 评论点赞
    int SetCommentStar (FollowComm followComm);
    // 评论是否点赞
    String FindCommentStar(String comment_id,String trends_id, String uid);
    // 删除一条评论点赞记录
    int DeleteCommentStar(String id);
    // 更新评论详情里的点赞 +1
    int UpdateCommentStar(Integer star,String id);
    // 关注
    int SetUserFollow(Followers followers);
    // 是否关注
    String FindIsFollow(String uid,String my_id);
    // 取消关注
    int DeleteFollow(String id);
    // 更新用户粉丝
    int UpdateUserFollowers(Integer followers,String uid);
    // 更新用户关注
    int UpdateUserFollowing(Integer following,String uid);
}
