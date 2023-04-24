package com.sunset.mapper;

import com.sunset.entity.Know.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowMapper {
    // 发布文章 【后台端】
    int SetKnow(KnowEntity knowEntity);
    // 删除文章
    int DeleteKnow(String id);
    // 更新文章
    int UpdateKnow(KnowEntity knowEntity);
    // 文章列表
    List<KnowEntity> GetKnow(PageKnow pageKnow);
    // 文章详情
    KnowIsLike GetKnowDetail(String id);
    // 文章阅读数
    int SetKnowRead(int read_num, String id);
    // 收藏
    int SetKnowLike(LikeKnow likeKnow);
    // 是否收藏
    String FindIsLike(String know_id, String uid);
    // 取消收藏 【删除收藏一条当前记录】
    int DeleteKnowLike(String id);
    // 更新列表收藏数量
    int UpdateKnowLike(int like_num,String id);
    // 我的收藏
    List<LikeKnow> GetLike(PageKnow pageKnow);
}
