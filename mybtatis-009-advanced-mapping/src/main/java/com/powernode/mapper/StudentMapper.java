package com.powernode.mapper;

import com.powernode.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface StudentMapper {

    List<Student> selectByIdStep2(Integer id);


    /**
     * 分步查询第一步：先根据学生sid查询学生信息
     * @param id 学生id
     * @return 学生信息
     */
    Student selectByIdStep1(Integer id);

    /**
     * 通过一条SQL语句，association方式进行高级映射查询
     * @param id 学生id
     * @return 学生信息
     */
    Student selectByIdAssociation(Integer id);

    /**
     * 根据学生id获取学生信息，并且获取学生所关联的班级(级联属性映射)
     * @param id 学生id
     * @return 学生信息
     */
    Student selectById(Integer id);
}
