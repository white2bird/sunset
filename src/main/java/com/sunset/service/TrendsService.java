package com.sunset.service;

import com.sunset.entity.Trends.NewTrends;
import com.sunset.entity.User.UserFollow;
import com.sunset.mapper.TrendsMapper;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrendsService {
    @Autowired
    TrendsMapper trendsMapper;
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

    public ReturnJson<String> setTrends(PubTrends pubTrends){
//        NewTrends  s = trendsMapper.SetTrends(pubTrends);
        return ReturnJson.success(null,"ok");
    }
    // 用于返回的用户关注，粉丝，获赞的新实体类
    @Data
    public class UserFollows{
        private String uid;
        @Schema(description = "关注")
        private String following = "0";
        @Schema(description = "粉丝")
        private String followers = "0";
        @Schema(description = "获赞")
        private String star = "0";
    }

    @Data
    public class PubTrends{
        @Schema(description = "动态")
        private String text;
        @Schema(description = "上传的多图")
        private String[] images;
    }
}
