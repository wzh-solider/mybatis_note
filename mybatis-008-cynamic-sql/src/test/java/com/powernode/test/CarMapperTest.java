package com.powernode.test;

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
    public void testDeleteByIds() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        String[] ids = {"1", "2", "3", "4"};
        int count = mapper.deleteByIds(ids);

        if (count > 0) {
            sqlSession.commit();
        }

        SqlSessionUtil.close();
    }

    @Test
    public void testSelectByChoose() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByChoose(null, null, "然汽车");
        cars.forEach(System.out::println);
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectByMultiCondition() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiCondition("200", null, "然汽车");
        cars.forEach(System.out::println);
        SqlSessionUtil.close();
    }

    @Test
    public void testUpdateBySet() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        // 使用set标签进行更新，如果传入值为null或''，则不更新此值的内容
        int count = mapper.updateBySet(new Car(3L, "兰博基尼", "jain", null, null, null));

        if (count > 0) {
            sqlSession.commit();
        }
        System.out.println(count);
        SqlSessionUtil.close();
    }

    @Test
    public void testInsertData() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.insertData(new Car(9L, "1000", "abd", 200.00, "2002-12-20", "新能源"));
        if (count > 0) {
            sqlSession.commit();
        }
        SqlSessionUtil.close();
    }
}
