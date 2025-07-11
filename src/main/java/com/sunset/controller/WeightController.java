package com.sunset.controller;


import com.sunset.request.*;
import com.sunset.service.WeightService;
import com.sunset.utils.AuthMsToken;
import com.sunset.utils.ReturnJson;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/weight")
public class WeightController {

    @Resource
    private WeightService weightService;

    @AuthMsToken
    @Operation(summary = "保存体重")
    @PostMapping("/save")
    public ReturnJson save(@Valid @RequestBody WeightRequest weightRequest){
        return ReturnJson.success(weightService.saveWeight(weightRequest, true));
    }

    @Operation(summary = "获取最近的一次信息")
    @AuthMsToken
    @GetMapping("/latest_weight_info")
    public ReturnJson latestWeighInfo(){
        return ReturnJson.success(weightService.getLatestWeighInfo());
    }


    @Operation(summary = "获取最近的一次称重")
    @AuthMsToken
    @GetMapping("/latest_weight")
    public ReturnJson latestWeigh(){
        return ReturnJson.success(weightService.getLatestWeigh());
    }

    @Operation(summary = "称重历史")
    @AuthMsToken
    @PostMapping("/weight_history")
    public ReturnJson weight_history(@RequestBody @Valid WeightHistoryPageQuery weightHistoryPageQuery){
        return ReturnJson.success(weightService.weightHistory(weightHistoryPageQuery));
    }

    @Operation(summary = "变化曲线(bmi 体重 体脂率等)")
    @AuthMsToken
    @PostMapping("/change_trend")
    public ReturnJson changeTrend(@RequestBody @Valid HistoryTrendRequest weightHistoryPageQuery){
        return ReturnJson.success(weightService.historyTrend(weightHistoryPageQuery));
    }

    @Operation(summary = "删除称重")
    @AuthMsToken
    @PostMapping("/delete_history")
    public ReturnJson deleteHistory(@RequestBody DeleteHistoryWeightRequest deleteHistoryWeightRequest){
        return ReturnJson.success(weightService.deleteHistory(deleteHistoryWeightRequest));
    }

    @Operation(summary = "对比")
    @AuthMsToken
    @PostMapping("/compare")
    public ReturnJson compare(@RequestBody @Valid WeightCompareRequest weightCompareRequest){
        return ReturnJson.success(weightService.weightCompare(weightCompareRequest));
    }



}
