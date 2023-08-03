package com.powernode.test;

import com.powernode.bean.Car;
import com.powernode.mapper.CarMapper;
import com.powernode.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CarMapperTest {

    /**
     * 测试二级缓存
     */
    @Test
    public void testSelectById2() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("mybatis-config.xml"));

        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();

        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById2(8L);
        System.out.println(car);

        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById2(8L);
        System.out.println(car1);

        sqlSession.close();
        sqlSession1.close();
    }

    /**
     * 通过不同的SqlSession对象，测试MyBatis的一级缓存
     */
    @Test
    public void testSelectById() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("mybatis-config.xml"));

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);

        // 这行代码执行完毕后，数据会放到一级缓存（SqlSession）中
        Car car1 = mapper1.selectById(8L);
        System.out.println(car1);

        // 如果，这里不关闭sqlSession1对象的话，二级缓存还是没有数据

        // 从这里关闭后，sqlSession1的数据会写入到二级缓存
        sqlSession1.close();

        // 这行代码执行完毕后，数据同样会放到一级缓存中
        Car car2 = mapper2.selectById(8L);
        System.out.println(car2);

        // 当程序执行到这时，会将数据从一级缓存写入二级缓存中
        // sqlSession1.close();
        sqlSession2.close();
    }

//    @Test
//    public void testSelectById() {
//
//        // 通过工具类，获取同一个SqlSession对象
//        SqlSession sqlSession = SqlSessionUtil.openSession();
//        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
//        Car car = mapper.selectById(8L);
//        System.out.println(car);
//
//        SqlSession sqlSession1 = SqlSessionUtil.openSession();
//        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
//        Car car1 = mapper1.selectById(8L);
//        System.out.println(car1);
//        SqlSessionUtil.close();
//    }
}
