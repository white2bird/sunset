package com.sunset.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Tag(name = "Goods")
@RestController
@RequestMapping("/goods")
@ResponseBody
@Slf4j
public class JsonDataController {

    @Operation(summary = "发现--好物列表")
    @GetMapping("/get_shopp")
    public ReturnJson<List> getGoodShopp() throws IOException {
//        ClassPathResource classPathResource = new ClassPathResource("static/json/goodShopp.json");
//        InputStream inputStream = classPathResource.getInputStream();
        File file = new File("src/main/resources/json/goodShopp.json");
        String jsonData = FileUtils.readFileToString(file);
        List list = JSONObject.parseArray(jsonData); // JSON 转换 List
        return ReturnJson.success(list, "ok");
    }
    @Operation(summary = "首页--轮播图")
    @GetMapping("/get_banner")
    public ReturnJson<List> getBannerShopp() throws IOException {
//        ClassPathResource classPathResource = new ClassPathResource("static/json/goodShopp.json");
//        InputStream inputStream = classPathResource.getInputStream();
        File file = new File("src/main/resources/json/banner.json");
        String jsonData = FileUtils.readFileToString(file);
        JSONArray list = JSONObject.parseArray(jsonData); // JSON 转换 List
        return ReturnJson.success(list, "ok");
    }

}
