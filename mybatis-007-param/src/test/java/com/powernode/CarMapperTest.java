package com.powernode;

import com.powernode.bean.Car;
import com.powernode.mapper.CarMapper;
import com.powernode.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CarMapperTest {
    @Test
    public void testSelectAllByResultMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByResultMap();
        cars.forEach(car -> System.out.println(car));
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectAllRetMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map<Long, Map<String, Object>> map = mapper.selectAllRetMap();
        System.out.println(map);
        SqlSessionUtil.close();
    }
}
