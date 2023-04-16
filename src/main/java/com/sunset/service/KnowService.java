package com.sunset.service;

import com.sunset.entity.Know.KnowEntity;
import com.sunset.mapper.KnowMapper;
import com.sunset.mapper.TrendsMapper;
import com.sunset.utils.ReturnJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class KnowService {
    @Autowired
    KnowMapper knowMapper;
    // 发布文章
    public ReturnJson<String> SetKnow(KnowEntity knowEntity, HttpServletRequest request){

        return ReturnJson.success(null,"o");
    }
}
