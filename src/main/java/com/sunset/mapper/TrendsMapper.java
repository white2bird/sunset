package com.sunset.mapper;

import com.sunset.entity.Trends.NewTrends;
import com.sunset.entity.User.UserFollow;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendsMapper {

    // 获取用户关注，粉丝，获赞
    UserFollow GetUserFollw(String uid);
    // 发布动态
    NewTrends SetTrends(NewTrends newTrends);
}
