package com.powernode.mapper;

import com.powernode.bean.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface CarMapper {

    /**
     * 使用foreach进行循环批量删除
     * @param ids 传入的id数组
     * @return int
     */
    int deleteByIds(@Param("ids") String[] ids);


    /**
     * 使用choose、when、otherwise进行查询
     * @param brand 品牌
     * @param guidePrice 指导价
     * @param carType 汽车类型
     * @return List<Car>
     */
    List<Car> selectByChoose(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);


    int updateBySet(Car car);

    /**
     * 使用trim标签，进行多条件查询
     * @param brand 品牌
     * @param guidePrice 指导价
     * @param carType 汽车类型
     * @return List<Car>
     */
    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    /**
     * 使用where标签，进行多条件查询
     * @param brand 品牌
     * @param guidePrice 指导价
     * @param carType 汽车类型
     * @return List<Car>
     */
    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);


    /**
     * 多条件查询
     * @param brand 品牌
     * @param guidePrice 指导价
     * @param carType 汽车类型
     * @return List<Car>
     */
    List<Car> selectByMultiCondition(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    int insertData(Car car);
}
