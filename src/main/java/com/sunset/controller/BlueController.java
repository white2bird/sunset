package com.sunset.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sunset.entity.blue.BlueEntity;
import com.sunset.request.BlueRequest;
import com.sunset.service.BlueService;
import com.sunset.utils.AuthMsToken;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.UserIdThreadLocal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "蓝牙管理")
@RestController
@RequestMapping("/blue")
public class BlueController {
    
    @Resource
    private BlueService blueService;

    @GetMapping("/list")
    @AuthMsToken
    @Operation(description = "获取用户历史绑定的蓝牙")
    public ReturnJson list(){

        LambdaQueryWrapper<BlueEntity> blueEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blueEntityLambdaQueryWrapper.eq(BlueEntity::getUid, UserIdThreadLocal.getUserId());
        List<BlueEntity> blueEntities = blueService.list(blueEntityLambdaQueryWrapper);
        List<String> result = blueEntities.stream().map(BlueEntity::getBlueAddress).collect(Collectors.toList());
        return ReturnJson.success(result);
    }

    @PostMapping("/bind")
    @AuthMsToken
    @Operation(description = "蓝牙绑定")
    public ReturnJson bind(@Valid @RequestBody BlueRequest blueRequest){
        BlueEntity blueEntity = new BlueEntity();
        blueEntity.setBlueAddress(blueRequest.getBlueAddress());
        blueEntity.setUid(UserIdThreadLocal.getUserId());
        blueService.save(blueEntity);
        return ReturnJson.success(null);
    }

}
