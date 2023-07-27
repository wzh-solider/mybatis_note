package com.powernode.mapper;

import com.powernode.bean.Car;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface CarMapper {

   List<Car> selectAllByResultMap();

    /**
     * @MapKey("id") 设置大Map集合的key值为小Map集合中的id值
     */
   @MapKey("id")
   Map<Long, Map<String, Object>> selectAllRetMap();
}
