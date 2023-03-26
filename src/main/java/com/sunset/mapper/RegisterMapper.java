package com.sunset.mapper;

import com.sunset.entity.Register;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterMapper {
    int RegisterInsert(Register register);
    Register RegisterFindPhone(String phone);
}
