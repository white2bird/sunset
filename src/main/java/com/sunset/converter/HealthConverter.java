package com.sunset.converter;

import com.sunset.entity.HealthData2;
import com.sunset.response.HealthDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper
public interface HealthConverter {
//    static final HealthConverter  INSTANCE = Mappers.getMapper(HealthConverter.class);
//
//
//    @Mapping(target = "measureTime", source = "measureTime")
//    @Mapping(target = "time", expression = "java(formatToMonthDay(measureTime))")
//    HealthDataResponse toResponse(HealthData2 healthData);
//
//    List<HealthDataResponse> toResponseList(List<HealthData2> healthDataList);
//
//    default String formatToMonthDay(String measureTime) {
//        if (measureTime == null || measureTime.isEmpty()) {
//            return null;
//        }
//        // 假设 measureTime 格式为 "yyyy-MM-dd HH:mm:ss"
//        LocalDateTime dateTime = LocalDateTime.parse(measureTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        return dateTime.format(DateTimeFormatter.ofPattern("MM-dd"));
//    }
}
