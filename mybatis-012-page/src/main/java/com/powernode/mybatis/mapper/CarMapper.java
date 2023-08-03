package com.powernode.mybatis.mapper;

import com.powernode.mybatis.bean.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface CarMapper {

    /**
     * 使用PageHelper查询所有
     */
    List<Car> selectAll();

    List<Car> selectByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}
