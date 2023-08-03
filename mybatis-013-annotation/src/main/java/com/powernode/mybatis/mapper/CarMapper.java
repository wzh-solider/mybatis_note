package com.powernode.mybatis.mapper;

import com.powernode.mybatis.bean.Car;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface CarMapper {

    @Select("select * from t_car where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "carNum", column = "car_num"),
            @Result(property = "brand", column = "brand"),
            @Result(property = "guidePrice", column = "guide_price"),
            @Result(property = "produceTime", column = "produce_time"),
            @Result(property = "carType", column = "car_type")
    })
    Car selectById(@Param("id") Long id);
}
