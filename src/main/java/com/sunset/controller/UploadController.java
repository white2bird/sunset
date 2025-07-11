package com.sunset.controller;

import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Tag(name = "Upload")
@RestController
@RequestMapping("/upload")
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class UploadController {
//    String uploadImageUrl = "/src/main/resources/static/images/";
//    String uploadAvatorUrl = "/src/main/resources/static/avator/";
    String uploadImageUrl = "/upload/images/";
    String uploadAvatorUrl = "/upload/avator/";
    @Operation(summary = "上传图片【单图】")
    @PostMapping("/image")
    public ReturnJson<Map> UploadImage(@RequestParam("file") MultipartFile fImage) throws IOException {
       Map map= getImageMap(fImage,uploadImageUrl,"/images/");
        return ReturnJson.success(map,"ok");
    }
    @Operation(summary = "上传头像")
    @PostMapping("/avator")
    public ReturnJson<Map> UploadAvator(@RequestParam("file") MultipartFile fImage) throws IOException {

        Map map= getImageMap(fImage,uploadAvatorUrl,"/avator/");


        return ReturnJson.success(map,"ok");
    }
    @Operation(summary = "多图上传【多图】")
    @PostMapping("/image_s")
    public ReturnJson<List> UploadImage(@RequestParam("file") List<MultipartFile> fImage) throws IOException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < fImage.size(); i++) {
            MultipartFile mf = fImage.get(i);
            Map map= getImageMap(mf,uploadImageUrl,"/images/");
            list.add((String) map.get("path"));
        }
        return ReturnJson.success(list,"ok");
    }

    public Map getImageMap(MultipartFile params,String uploadUrl,String setPath) throws IOException {
        // 文件名
        String fName = params.getOriginalFilename();
        // 原始后缀名
        String suffixName = fName.substring(fName.lastIndexOf("."));
        // 重新生成后缀名
        String uuid = UUID.randomUUID() +"-"+ System.currentTimeMillis()+suffixName;
        // 存储的绝对路径
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        String path = System.getProperty("user.dir")+uploadUrl; // 开发环境
//        String path = jarF.getParentFile().toString()+uploadUrl; // 生产环境
        String fuuid = path + uuid;
        HashMap<String,String> map = new HashMap<>();
        map.put("path",setPath+uuid);
        map.put("name",fName);
        File file = new File(fuuid);
        // 保存到目录中
        params.transferTo(file);
        return map;
    }
}
