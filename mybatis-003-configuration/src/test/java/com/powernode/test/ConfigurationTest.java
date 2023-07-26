package com.powernode.test;

import com.powernode.mybatis.bean.Car;
import com.powernode.mybatis.bean.Class;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class ConfigurationTest {
    @Test
    public void testCarMapperSelectAll() throws Exception{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(
                Resources.getResourceAsStream("mybatis-config.xml"),
                "bjpowernode");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Class> classList= sqlSession.selectList("class.selectAll");
        classList.forEach(System.out::println);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testPooledAndUnPolled() throws Exception{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory =
                sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        for (int i = 0; i < 4; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            int insert = sqlSession.insert("car.insertCar");
            System.out.println(insert + " " + (i + 1));
        }
    }
}
