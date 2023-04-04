package com.sunset.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Tag(name = "Goods")
@RestController
@RequestMapping("/goods")
@ResponseBody
@Slf4j
public class JsonDataController {

    @Operation(summary = "发现--好物列表")
    @GetMapping("/get_shopp")
    public ReturnJson<List> getGoodShopp() throws IOException {
        // 获取绝对路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        File file = new File(path+"/json/goodShopp.json");
        String jsonData = FileUtils.readFileToString(file);
        JSONArray list = JSONObject.parseArray(jsonData); // JSON 转换 List
        return ReturnJson.success(list, "ok");
    }
    @Operation(summary = "首页--轮播图")
    @GetMapping("/get_banner")
    public ReturnJson<List> getBannerShopp() throws IOException {
        // 获取绝对路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        File file = new File(path+"/json/banner.json");
        String jsonData = FileUtils.readFileToString(file);
        JSONArray list = JSONObject.parseArray(jsonData); // JSON 转换 List
        return ReturnJson.success(list, "ok");
    }

}
