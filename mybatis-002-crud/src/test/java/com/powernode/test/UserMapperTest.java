package com.powernode.test;

import com.powernode.mybatis.bean.Car;
import com.powernode.mybatis.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class UserMapperTest {
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = null;

        try {
            // 获取会话
            sqlSession = SqlSessionUtil.openSession();
            // 执行sql
            List<Car> cars= sqlSession.selectList("userMapper.selectAll");

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
}
