package com.powernode.mapper;

import com.powernode.bean.Log;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface LogMapper {

    List<Log> selectAllByTable(String time);
}
