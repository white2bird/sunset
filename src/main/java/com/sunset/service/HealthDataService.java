package com.sunset.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunset.entity.HealthData2;
import com.sunset.mapper.HealthDataMapper;
import org.springframework.stereotype.Service;


@Service
public class HealthDataService extends ServiceImpl<HealthDataMapper, HealthData2> {

    public void saveHealthData(HealthData2 healthData){
        this.save(healthData);
    }
}
