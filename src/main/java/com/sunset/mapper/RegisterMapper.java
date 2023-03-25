package com.sunset.mapper;

import com.sunset.entity.Register;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterMapper {
    Boolean add(Register register);
}
