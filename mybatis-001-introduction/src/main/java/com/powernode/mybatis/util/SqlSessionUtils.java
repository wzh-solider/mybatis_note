package com.powernode.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * MyBatis工具类
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class SqlSessionUtils {
    private static SqlSessionFactory sqlSessionFactory;

    // 构造方法私有化
    private SqlSessionUtils() {}

    // 类加载时，创建SqlSessionFactory对象
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 开启事务的会话对象
     * @return SqlSession对象
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 如果执行异常，进行事务回滚
     * @param sqlSession 会话对象
     */
    public static void rollback(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.rollback();
        }
    }

    /**
     * 执行完毕，提交事务
     * @param sqlSession 会话对象
     */
    public static void commit(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }

    /**
     * 关闭会话对象，释放资源
     * @param sqlSession 会话对象
     */
    public static void close(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
