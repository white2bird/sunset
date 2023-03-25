package com.example.sunset.mapper;

import com.example.sunset.entity.Register;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface RegisterMapper {
    Boolean add(Register register);
}
