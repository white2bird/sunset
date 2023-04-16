package com.sunset.controller;

import com.sunset.entity.Know.KnowEntity;
import com.sunset.entity.Know.KnowParams;
import com.sunset.entity.Trends.PubTrends;
import com.sunset.service.KnowService;
import com.sunset.utils.AuthMsToken;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Know")
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
    // 发布文章
    public ReturnJson<String> SetKnow(@RequestBody KnowParams knowParams, HttpServletRequest request) {
        return knowService.SetKnow(knowParams, request);
    }
}
