package com.sunset.mapper;

import com.sunset.entity.Trends.NewTrends;
import com.sunset.entity.Trends.PageRends;
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
}
