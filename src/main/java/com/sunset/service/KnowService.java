package com.sunset.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunset.entity.Know.*;
import com.sunset.entity.Trends.ListTrends;
import com.sunset.mapper.KnowMapper;
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
    public ReturnJson<String> SetKnow(KnowParams kp, HttpServletRequest request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        String uuid = UUID.randomUUID().toString().toUpperCase();
        KnowEntity k = new KnowEntity();
        k.setId(uuid);
        k.setTitle(kp.getTitle());
        k.setUrl(kp.getUrl());
        k.setType(kp.getType());
        k.setContent(kp.getContent());
        k.setCover_img(kp.getCover_img());
        k.setIsthird(kp.getIsthird());
        k.setCreate_time(dateTime);
        knowMapper.SetKnow(k);


        return ReturnJson.success(null, "ok");
    }

    // 文章列表
    public ReturnJson<ListTrends<KnowEntity>> GetKnow(PageKnow pageKnow) {
        // 时间倒序
        String orby = "create_time desc";
        // 分页查询
        PageHelper.startPage(pageKnow.getPage_num(), pageKnow.getPage_rows(), orby);

        List<KnowEntity> list = knowMapper.GetKnow(pageKnow);
        PageInfo<KnowEntity> pageInfo = new PageInfo<>(list);
        List<KnowEntity> lists = pageInfo.getList();

        ListTrends listTrends = new ListTrends();
        listTrends.setTotal(pageInfo.getTotal());
        listTrends.setList(lists);
        return ReturnJson.success(listTrends, "ok");
    }

    // 文章详情
    public ReturnJson<KnowIsLike> GetKnowDetail(String id, HttpServletRequest request) {
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        String islike = knowMapper.FindIsLike(id,uid);
        KnowIsLike k = knowMapper.GetKnowDetail(id);
        if (k == null) {
            return ReturnJson.fail(-1, "文章不存在");
        }
        // 收藏记录存在 并且 有token未失效
        if(islike != null && uid != null){
            k.setIslike(true);
        }else{
            k.setIslike(false);
        }
        int num = k.getRead_num();
        // 增加阅读数
        num++;
        int isSet = knowMapper.SetKnowRead(num, id);
        if (isSet == 1) {
            // 保持阅读数同步
            k.setRead_num(k.getRead_num() + 1);
        }
        return ReturnJson.success(k, "ok");
    }

    // 收藏文章 & 取消收藏文章
    public ReturnJson<String> SetKnowLike(String id, HttpServletRequest request) {
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");

        KnowEntity k = knowMapper.GetKnowDetail(id);
        if (k == null) {
            return ReturnJson.fail(-1, "文章不存在");
        }
        String like_id = knowMapper.FindIsLike(id, uid);
        LikeKnow likeKnow = new LikeKnow();
        int like_num = k.getLike_num();
        if (like_id != null) {
            like_num--;
            knowMapper.DeleteKnowLike(like_id);
            // Math.max 如果表中查到的收藏数量小于0就初始为0否则相减
            knowMapper.UpdateKnowLike(Math.max(like_num, 0), k.getId());
            return ReturnJson.success(null, "取消收藏ok");
        }
        like_num++;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        String uuid = UUID.randomUUID().toString().toUpperCase();
        likeKnow.setId(uuid);
        likeKnow.setUid(uid);
        likeKnow.setKnow_id(id);
        likeKnow.setCreate_time(dateTime);
        knowMapper.UpdateKnowLike(Math.max(like_num, 0), k.getId());
        knowMapper.SetKnowLike(likeKnow);
        return ReturnJson.success(null, "收藏ok");
    }
    // 我的收藏
    public ReturnJson<ListTrends<KnowEntity>> GetLike(MyLike myLike,HttpServletRequest request){
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        // 时间倒序
        String orby = "create_time desc";
        // 分页查询
        PageHelper.startPage(myLike.getPage_num(), myLike.getPage_rows(), orby);
        myLike.setUid(uid);
        List<LikeKnow> list = knowMapper.GetLike(myLike);
        PageInfo<LikeKnow> pageInfo = new PageInfo<>(list);
        List<LikeKnow> lists = pageInfo.getList();
        List<KnowEntity> likeList = new ArrayList<>();
        lists.forEach(x->{
            KnowEntity k = new KnowEntity();
            KnowEntity kw = knowMapper.GetKnowDetail(x.getKnow_id());
            if(kw != null){
                k.setId(kw.getId());
                k.setCover_img(kw.getCover_img());
                k.setIsthird(kw.getIsthird());
                k.setTitle(kw.getTitle());
                k.setUrl(kw.getUrl());
                k.setType(kw.getType());
                k.setRead_num(kw.getRead_num());
                k.setLike_num(kw.getLike_num());
                k.setCreate_time(x.getCreate_time());
                likeList.add(k);
            }
        });
        ListTrends listTrends = new ListTrends();
        listTrends.setTotal(pageInfo.getTotal());
        listTrends.setList(likeList);
        return ReturnJson.success(listTrends,"ok");
    }
}
