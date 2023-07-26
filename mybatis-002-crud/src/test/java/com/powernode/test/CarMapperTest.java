package com.powernode.test;

import com.powernode.mybatis.bean.Car;
import com.powernode.mybatis.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CarMapperTest {
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = null;

        try {
            // 获取会话
            sqlSession = SqlSessionUtil.openSession();
            // 执行sql
            List<Car> cars= sqlSession.selectList("selectAll");

            // 判读是否执行成功，执行失败抛出异常捕获
            if (cars != null) {
                System.out.println("查询成功");
                cars.forEach(System.out::println);
            } else {
                throw new RuntimeException();
            }

            // 提交事务
            SqlSessionUtil.commit(sqlSession);
        } catch (Exception e) {
            // 回滚事务
            SqlSessionUtil.rollback(sqlSession);
            e.printStackTrace();
        } finally {
            // 关闭SqlSession对象
            SqlSessionUtil.close(sqlSession);
        }
    }

    @Test
    public void testSelectOneCar() {
        SqlSession sqlSession = null;

        try {
            // 获取会话
            sqlSession = SqlSessionUtil.openSession();
            // 执行sql
            Car car = sqlSession.selectOne("selectOneCar", 5);

            // 判读是否执行成功，执行失败抛出异常捕获
            if (car != null) {
                System.out.println("查询成功");
                System.out.println(car);
            } else {
                throw new RuntimeException();
            }
            // 提交事务
            SqlSessionUtil.commit(sqlSession);
        } catch (Exception e) {
            // 回滚事务
            SqlSessionUtil.rollback(sqlSession);
            e.printStackTrace();
        } finally {
            // 关闭SqlSession对象
            SqlSessionUtil.close(sqlSession);
        }
    }

    @Test
    public void testUpdateCarById() {
        SqlSession sqlSession = null;
        Car car = new Car(1L, "2222", "haahah", 12.22, "2003-02-10", "消防车");

        try {
            // 获取会话
            sqlSession = SqlSessionUtil.openSession();
            // 执行sql
            int count = sqlSession.update("updateCarById", car);

            // 判读是否执行成功，执行失败抛出异常捕获
            if (count > 0) {
                System.out.println("执行成功");
            } else {
                throw new RuntimeException();
            }
            // 提交事务
            SqlSessionUtil.commit(sqlSession);
        } catch (Exception e) {
            // 回滚事务
            SqlSessionUtil.rollback(sqlSession);
            e.printStackTrace();
        } finally {
            // 关闭SqlSession对象
            SqlSessionUtil.close(sqlSession);
        }
    }

    @Test
    public void testDeleteCarById() {
        SqlSession sqlSession = null;

        try {
            // 获取会话
            sqlSession = SqlSessionUtil.openSession();
            // 执行sql
            int count = sqlSession.delete("deleteCarById", 14);

            // 判读是否执行成功，执行失败抛出异常捕获
            if (count > 0) {
                System.out.println("执行成功");
            } else {
                throw new RuntimeException();
            }
            // 提交事务
            SqlSessionUtil.commit(sqlSession);
        } catch (Exception e) {
            // 回滚事务
            SqlSessionUtil.rollback(sqlSession);
            e.printStackTrace();
        } finally {
            // 关闭SqlSession对象
            SqlSessionUtil.close(sqlSession);
        }
    }

    @Test
    public void testInsertCarByBean() {
        SqlSession sqlSession = null;
        Car car = new Car(null, "2222", "haahah", 12.22, "2003-02-10", "消防车");

        try {
            // 获取会话
            sqlSession = SqlSessionUtil.openSession();
            // 执行sql
            int count = sqlSession.insert("insertCar", car);

            // 判读是否执行成功，执行失败抛出异常捕获
            if (count > 0) {
                System.out.println("执行成功");
            } else {
                throw new RuntimeException();
            }
            // 提交事务
            SqlSessionUtil.commit(sqlSession);
        } catch (Exception e) {
            // 回滚事务
            SqlSessionUtil.rollback(sqlSession);
            e.printStackTrace();
        } finally {
            // 关闭SqlSession对象
            SqlSessionUtil.close(sqlSession);
        }
    }

    @Test
    public void testInsertCarByUtil() {
        SqlSession sqlSession = null;

        Map<String, Object> map = new HashMap<>();
        map.put("car_num", "001");
        map.put("brand", "baoma");
        map.put("guide_price", 100.0);
        map.put("produce_time", "2023-10-20");
        map.put("car_type", "新能源汽车");

        try {
            // 获取会话
            sqlSession = SqlSessionUtil.openSession();
            // 执行sql
            int count = sqlSession.insert("insertCar", map);

            // 判读是否执行成功，执行失败抛出异常捕获
            if (count > 0) {
                System.out.println("执行成功");
            } else {
                throw new RuntimeException();
            }
            // 提交事务
            SqlSessionUtil.commit(sqlSession);
        } catch (Exception e) {
            // 回滚事务
            SqlSessionUtil.rollback(sqlSession);
            e.printStackTrace();
        } finally {
            // 关闭SqlSession对象
            SqlSessionUtil.close(sqlSession);
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
