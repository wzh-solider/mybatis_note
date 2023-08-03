package com.powernode.mybatis.test;

import com.powernode.mybatis.bean.Car;
import com.powernode.mybatis.mapper.CarMapper;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CarMapperTest {

    @Test
    public void testSelectById() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        Car car = mapper.selectById(8L);
        System.out.println(car);
        SqlSessionUtil.close();
    }
}
