package com.sunset.service;

import com.sunset.entity.User.UserFollow;
import com.sunset.entity.User.UserInfoEntity;
import com.sunset.mapper.SignMapper;
import com.sunset.mapper.TrendsMapper;
import com.sunset.utils.ReturnJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TrendsService {
    @Autowired
    TrendsMapper trendsMapper;

    public ReturnJson<Map> getUserFollow(String uid) {
        UserFollow userFollow = trendsMapper.GetUserFollw(uid);
        HashMap<String, String> map = new HashMap<>();
        map.put("following", userFollow.getFollowing());
        map.put("followers", userFollow.getFollowers());
        map.put("start", userFollow.getStar());
        if (userFollow == null) {
            return ReturnJson.fail(-1, "fail");
        }

        return ReturnJson.success(map, "ok");
    }
}
