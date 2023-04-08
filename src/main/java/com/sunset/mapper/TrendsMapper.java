package com.sunset.mapper;

import com.sunset.entity.Trends.*;
import com.sunset.entity.User.UserFollow;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrendsMapper {

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
}
