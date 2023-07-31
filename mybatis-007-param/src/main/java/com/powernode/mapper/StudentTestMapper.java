package com.powernode.mapper;

import com.powernode.bean.StudentTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface StudentTestMapper {
    /**
     * 查询所有学生
     * @return 学生的List集合
     */
    List<StudentTest> selectAll();

    // 根据传入参数，查询指定内容
    List<StudentTest> selectById(Long id);

    List<StudentTest> selectByName(String name);

    List<StudentTest> selectByAge(Integer age);

    List<StudentTest> selectBySex(String sex);

    // Map集合插入
    int insertByMap(Map<String, Object> map);

    // 多参数查找
    List<StudentTest> selectByNameAndSex(String name, String sex);

    /**
     * 多参数注解开发
     * @Param 用于指定在底层生成的Map集合的key值
     * 会使得arg0、arg1...失效，但param1、param2...仍然可以使用
     */
    List<StudentTest> selectByNameAndSex2(@Param("name") String name, @Param("sex") String sex);
}
