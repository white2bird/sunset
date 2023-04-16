package com.sunset.mapper;

import com.sunset.entity.Know.KnowEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowMapper {
    // 发布文章 【后台端】
    int SetKnow(KnowEntity knowEntity);
}
