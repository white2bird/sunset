package com.sunset.controller;

import com.sunset.service.TrendsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Trends")
@RestController
@RequestMapping("/trends")
@ResponseBody
@Slf4j
public class TrendsController {
    @Autowired
    TrendsService trendsService;
//    @Operation(summary = "动态列表")
//    @GetMapping("/get/list")
//    public ReturnJson<String> get_trendsList() {
//        return trendsService.GetTrendsList();
//    }
}
