package com.sunset.mapper;

import com.sunset.entity.Register;
import com.sunset.utils.ReturnJson;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterMapper {
    int RegisterInsert(Register register);
}
