package com.powernode.test;

import com.powernode.bean.Clazz;
import com.powernode.mapper.ClazzMapper;
import com.powernode.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class ClazzMapperTest {

    @Test
    public void testSelectByIdStep1() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByIdStep1(1001);
        System.out.println(clazz);
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectByCollection() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCollection(1001);
        System.out.println(clazz);
        SqlSessionUtil.close();
    }
}
