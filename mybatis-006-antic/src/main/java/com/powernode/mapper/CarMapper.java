package com.powernode.mapper;

import com.powernode.bean.Car;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface CarMapper {

    /**
     * 增
     * @param car
     * @return 受影响的行数
     */
    int insert(Car car);

    /**
     * 通过id属性，删除数据
     * @param id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 修改汽车信息
     * @param car
     * @return
     */
    int update(Car car);

    /**
     * 根据id，查询Car
     * @param id
     * @return Car对象
     */
    Car selectById(Long id);

    /**
     * 查询所有的汽车信息
     * @return Car的List集合
     */
    List<Car> selectAll();
}
