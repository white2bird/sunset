package com.sunset.controller;

import com.sunset.entity.Know.KnowEntity;
import com.sunset.entity.Know.KnowParams;
import com.sunset.entity.Know.PageKnow;
import com.sunset.entity.Trends.ListTrends;
import com.sunset.entity.Trends.PageRends;
import com.sunset.entity.Trends.PubTrends;
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
    @AuthMsToken
    public ReturnJson<String> SetKnow(@RequestBody KnowParams knowParams, HttpServletRequest request) {
        return knowService.SetKnow(knowParams, request);
    }
    @Operation(summary = "文章列表")
    @GetMapping("/list")
    public ReturnJson<ListTrends<KnowEntity>> GetTrendslist(@RequestParam(name = "type", required = false) @Parameter(description="类型") String type, @Parameter(description="页码") Integer page_num, @Parameter(description="页数") Integer page_rows) {
        PageKnow p =new PageKnow();
        p.setType(type);
        p.setPage_num(page_num);
        p.setPage_rows(page_rows);
        return knowService.GetKonw(p);
    }
    @Operation(summary = "文章详情")
    @GetMapping("/detail")
    public ReturnJson<KnowEntity> GetKnowDetail(@RequestParam(name = "id") String id) {
        return knowService.GetKnowDetail(id);
    }
    @Operation(summary = "文章阅读数")
    @PostMapping("/detail")
    public ReturnJson<String> SetKnowRead(@RequestParam(name = "id") String id) {
        return knowService.SetKnowRead(id);
    }
}
