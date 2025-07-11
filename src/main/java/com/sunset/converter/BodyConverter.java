package com.sunset.converter;

import com.sunset.entity.BodyComposition;
import com.sunset.entity.HealthData2;
import com.sunset.response.HealthDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

@Mapper
public interface BodyConverter {
    static final BodyConverter INSTANCE = Mappers.getMapper(BodyConverter.class);


    @Mapping(target = "weightTime", source = "weightTime")
    @Mapping(target = "time", expression = "java(formatToMonthDay(healthData.getWeightTime()))")
    HealthDataResponse toResponse(BodyComposition healthData);

    List<HealthDataResponse> toResponseList(List<BodyComposition> healthDataList);

    default String formatToMonthDay(Date measureTime) {
        if (measureTime == null ) {
            return null;
        }
        // 假设 measureTime 格式为 "yyyy-MM-dd HH:mm:ss"
//        LocalDateTime dateTime = LocalDateTime.parse(measureTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        String format = simpleDateFormat.format(measureTime);
        return format;
    }
}
