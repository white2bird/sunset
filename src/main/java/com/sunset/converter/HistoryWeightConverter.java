package com.sunset.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunset.entity.BodyComposition;
import com.sunset.response.HistoryWeigh;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HistoryWeightConverter {

    HistoryWeightConverter INSTANCE = Mappers.getMapper(HistoryWeightConverter.class);

    @Mapping(target = "weightTime", expression = "java( handTime(bodyComposition.getWeightTime()) )")
    HistoryWeigh toHistoryWeigh(BodyComposition bodyComposition);

    List<HistoryWeigh> toHistoryWeighList(List<BodyComposition> bodyCompositions);

    Page<HistoryWeigh> toHistoryWeighPage(Page<BodyComposition> bodyCompositions);

//    @Named("handTime")
    default String handTime(Date originalDate){
        Instant instant = originalDate.toInstant();
//        System.out.println("UTC瞬间点: " + instant);

        // 2. 获取系统默认时区
        ZoneId systemTimeZone = ZoneId.systemDefault();
//        System.out.println("系统默认时区: " + systemTimeZone);

        // 3. 转换为系统时区的时间
        ZonedDateTime systemTime = ZonedDateTime.ofInstant(instant, systemTimeZone);
        DateTimeFormatter standardFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String standardFormat = systemTime.format(standardFormatter);
        return standardFormat;
    }
}
