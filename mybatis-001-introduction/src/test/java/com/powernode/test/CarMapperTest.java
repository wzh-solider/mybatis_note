package com.powernode.test;

import com.powernode.mybatis.util.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CarMapperTest {

    @Test
    public void testInsertCarByUtil() {

        SqlSession sqlSession = null;

        try {
            // 获取会话
            sqlSession = SqlSessionUtils.openSession();
            // 执行sql
            int count = sqlSession.insert("insertCar");

            // 判读是否执行成功，执行失败抛出异常捕获
            if (count > 0) {
                System.out.println("执行成功");
            } else {
                throw new RuntimeException();
            }
            // 提交事务
            SqlSessionUtils.commit(sqlSession);
        } catch (Exception e) {
            // 回滚事务
            SqlSessionUtils.rollback(sqlSession);
            e.printStackTrace();
        } finally {
            // 关闭SqlSession对象
            SqlSessionUtils.close(sqlSession);
        }
    }

    @Test
    public void testInsertCar() {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.
                    build(Resources.getResourceAsStream("mybatis-config.xml"));

            // 获取sqlSession对象
            sqlSession = sqlSessionFactory.openSession();
            int count = sqlSession.insert("insertCar");
            System.out.println(count);
            // 手动提交
            sqlSession.commit();
        } catch (Exception e) {
            // 事务回滚
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            // 关闭会话(释放资源)
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
