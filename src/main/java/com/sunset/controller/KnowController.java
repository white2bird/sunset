package com.sunset.controller;

import com.sunset.entity.Know.*;
import com.sunset.entity.Trends.ListTrends;
import com.sunset.service.KnowService;
import com.sunset.utils.AuthMsToken;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Know",description = "知识社区板块")
@RestController
@RequestMapping("/know")
@ResponseBody
@Slf4j
public class KnowController {
    @Autowired
    KnowService knowService;

    @Operation(summary = "发表文章【后台】")
    @PostMapping("pub")
    public ReturnJson<String> SetKnow(@RequestBody KnowParams knowParams, HttpServletRequest request) {
        return knowService.SetKnow(knowParams, request);
    }
    @Operation(summary = "文章列表")
    @GetMapping("/list")
    public ReturnJson<ListTrends<KnowEntity>> GetKnow(@RequestParam(name = "isimg", required = false) @Parameter(description="是否有封面") boolean isimg,@RequestParam(name = "type", required = false) @Parameter(description="类型") String type, @Parameter(description="页码") Integer page_num, @Parameter(description="页数") Integer page_rows) {
        PageKnow p =new PageKnow();
        p.setType(type);
        p.setPage_num(page_num);
        p.setPage_rows(page_rows);
        p.setIsimg(isimg);
        return knowService.GetKnow(p);
    }
    @Operation(summary = "我的收藏【文章收藏】")
    @GetMapping("/my_like")
    public ReturnJson<ListTrends<KnowEntity>> GetLike(@Parameter(description="页码") Integer page_num, @Parameter(description="页数") Integer page_rows,HttpServletRequest request) {
        MyLike p =new MyLike();
        p.setPage_num(page_num);
        p.setPage_rows(page_rows);
        return knowService.GetLike(p,request);
    }
    @Operation(summary = "文章详情")
    @GetMapping("/detail")
    public ReturnJson<KnowIsLike> GetKnowDetail(@RequestParam(name = "id") String id, HttpServletRequest request) {
        return knowService.GetKnowDetail(id,request);
    }
    @Operation(summary = "文章收藏<-->取消收藏")
    @PostMapping("/like")
    @AuthMsToken
    public ReturnJson<String> SetKnowLike(@RequestParam(name = "id") String id, HttpServletRequest request) {
        return knowService.SetKnowLike(id,request);
    }
}
