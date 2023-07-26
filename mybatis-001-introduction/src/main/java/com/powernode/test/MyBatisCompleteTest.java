package com.powernode.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 正规方式编写一个mybatis程序
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class MyBatisCompleteTest {
    public static void main(String[] args) {
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
