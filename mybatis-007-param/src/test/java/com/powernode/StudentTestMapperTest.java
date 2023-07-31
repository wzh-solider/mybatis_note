package com.powernode;

import com.powernode.bean.StudentTest;
import com.powernode.mapper.StudentTestMapper;
import com.powernode.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class StudentTestMapperTest {

    // 参数注解开发
    @Test
    public void testSelectByNameAndSex2() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        StudentTestMapper mapper = sqlSession.getMapper(StudentTestMapper.class);
        List<StudentTest> students = mapper.selectByNameAndSex2("jack", "男");
        students.forEach(System.out::println);

        SqlSessionUtil.close();
    }

    // 多参数查找
    @Test
    public void testSelectByNameAndSex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        StudentTestMapper mapper = sqlSession.getMapper(StudentTestMapper.class);
        List<StudentTest> studentTests = mapper.selectByNameAndSex("jack", "男");
        studentTests.forEach(System.out::println);

        SqlSessionUtil.close();
    }

    // Map集合插入
    @Test
    public void testInsertByMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Map<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", 10);
        map.put("height", 1.77);
        map.put("birth", "2003-12-20");
        map.put("sex", '男');

        StudentTestMapper mapper = sqlSession.getMapper(StudentTestMapper.class);
        int count = mapper.insertByMap(map);
        if (count > 0) {
            sqlSession.commit();
        }
        System.out.println(count);
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentTestMapper mapper = sqlSession.getMapper(StudentTestMapper.class);
        List<StudentTest> studentTests = mapper.selectAll();
        studentTests.forEach(System.out::println);
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectParameters() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentTestMapper mapper = sqlSession.getMapper(StudentTestMapper.class);
        List<StudentTest> studentTests = mapper.selectById(1L);
        studentTests.forEach(System.out::println);
        SqlSessionUtil.close();
    }
}
