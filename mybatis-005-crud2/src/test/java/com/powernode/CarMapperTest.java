package com.powernode;

import com.powernode.bean.Car;
import com.powernode.mapper.CarMapper;
import com.powernode.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CarMapperTest {
    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);

        carMapper.insert(new Car(4L, "比亚迪", "200", 3000.00, "2000-12-20", "燃油车"));
        // 提交
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        int count = mapper.deleteById(2L);
        System.out.println(count);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);

        carMapper.update(new Car(1L, "www", "w", 3000.00, "2003-12-20", "然汽车"));
        // 提交
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelect() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);

        System.out.println(carMapper.selectById(3L));
        carMapper.selectAll().forEach(System.out::println);
        // 提交
        sqlSession.commit();
    }
}
