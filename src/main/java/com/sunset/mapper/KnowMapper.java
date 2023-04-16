package com.sunset.mapper;

import com.sunset.entity.Know.KnowEntity;
import com.sunset.entity.Know.PageKnow;
import com.sunset.entity.Trends.ListTrends;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowMapper {
    // 发布文章 【后台端】
    int SetKnow(KnowEntity knowEntity);
    // 文章列表
    List<KnowEntity> GetKnow(PageKnow pageKnow);
    // 文章详情
    KnowEntity GetKnowDetail(String id);
}
