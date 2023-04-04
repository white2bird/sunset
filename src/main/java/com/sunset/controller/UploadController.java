package com.sunset.controller;

import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Upload")
@RestController
@RequestMapping("/upload")
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class UploadController {
    String uploadImageUrl = "/src/main/resources/static/images/";
    String uploadAvatorUrl = "/src/main/resources/static/avator/";
    @Operation(summary = "上传图片")
    @PostMapping("/image")
    public ReturnJson<Map> UploadImage(@RequestParam("file") MultipartFile fImage) throws IOException {
        // 文件名
        String fName = fImage.getOriginalFilename();
        // 原始后缀名
        String suffixName = fName.substring(fName.lastIndexOf("."));
        // 重新生成后缀名
        String uuid = UUID.randomUUID() +"-"+ System.currentTimeMillis()+suffixName;
        // 存储的绝对路径
        String path = System.getProperty("user.dir")+uploadImageUrl;
        String fuuid = path + uuid;
        HashMap<String,String> map = new HashMap<>();
        map.put("path","/images/"+uuid);
        map.put("name",fName);
        File file = new File(fuuid);
        // 保存到目录中
        fImage.transferTo(file);
        return ReturnJson.success(map,"ok");
    }
    @Operation(summary = "上传头像")
    @PostMapping("/avator")
    public ReturnJson<Map> UploadAvator(@RequestParam("file") MultipartFile fImage) throws IOException {
        // 文件名
        String fName = fImage.getOriginalFilename();
        // 原始后缀名
        String suffixName = fName.substring(fName.lastIndexOf("."));
        // 重新生成后缀名
        String uuid = UUID.randomUUID() +"-"+ System.currentTimeMillis()+suffixName;
        // 存储的绝对路径
        String path = System.getProperty("user.dir")+uploadAvatorUrl;
        String fuuid = path + uuid;
        HashMap<String,String> map = new HashMap<>();
        map.put("path","/avator/"+uuid);
        map.put("name",fName);
        File file = new File(fuuid);
        // 保存到目录中
        fImage.transferTo(file);
        return ReturnJson.success(map,"ok");
    }
}
