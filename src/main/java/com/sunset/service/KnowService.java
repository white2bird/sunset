package com.sunset.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunset.entity.Know.KnowEntity;
import com.sunset.entity.Know.KnowParams;
import com.sunset.entity.Know.PageKnow;
import com.sunset.entity.Trends.ListTrends;
import com.sunset.entity.Trends.NewTrends;
import com.sunset.entity.Trends.ObjTrends;
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
import java.util.ArrayList;
import java.util.List;
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
    // 文章列表
    public ReturnJson<ListTrends<KnowEntity>> GetKonw(PageKnow pageKnow){
        // 时间倒序
        String orby = "create_time desc";
        // 分页查询
        PageHelper.startPage(pageKnow.getPage_num(), pageKnow.getPage_rows(), orby);

        List<KnowEntity> list = knowMapper.GetKnow(pageKnow);
        PageInfo<KnowEntity> pageInfo = new PageInfo<>(list);
        List<KnowEntity> lists = pageInfo.getList();
        List<KnowEntity> newList = new ArrayList<>();


        ListTrends listTrends = new ListTrends();
        listTrends.setTotal(pageInfo.getTotal());
        listTrends.setList(lists);
        return ReturnJson.success(listTrends,"ok");
    }
    // 文章详情
    public ReturnJson<KnowEntity> GetKnowDetail(String id){
       KnowEntity k =  knowMapper.GetKnowDetail(id);
       if(k == null){
           return ReturnJson.fail(-1,"文章不存在");
       }
        return ReturnJson.success(k,"ok");
    }
}
