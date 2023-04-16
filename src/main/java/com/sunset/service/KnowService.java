package com.sunset.service;

import com.sunset.entity.Know.KnowEntity;
import com.sunset.entity.Know.KnowParams;
import com.sunset.mapper.KnowMapper;
import com.sunset.mapper.TrendsMapper;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class KnowService {
    @Autowired
    KnowMapper knowMapper;
    // 发布文章
    public ReturnJson<String> SetKnow(KnowParams kp, HttpServletRequest request){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        String uuid = UUID.randomUUID().toString().toUpperCase();
        KnowEntity k  = new KnowEntity();
        k.setId(uuid);
        k.setTitle(kp.getTitle());
        k.setUrl(kp.getUrl());
        k.setType(kp.getType());
        k.setContent(kp.getContent());
        k.setCover_img(kp.getCover_img());
        k.setIsthird(kp.getIsthird());
        k.setCreate_time(dateTime);
        knowMapper.SetKnow(k);


        return ReturnJson.success(null,"ok");
    }
}
