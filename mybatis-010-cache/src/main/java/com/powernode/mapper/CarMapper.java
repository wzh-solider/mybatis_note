package com.powernode.mapper;

import com.powernode.bean.Car;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface CarMapper {

    Car selectById2(Long id);

    Car selectById(Long id);
}
