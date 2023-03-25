package com.example.sunset.mapper;

import com.example.sunset.entity.Register;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

//@Mapper
@Repository
public interface RegisterMapper {
    Boolean add(Register register);
}
