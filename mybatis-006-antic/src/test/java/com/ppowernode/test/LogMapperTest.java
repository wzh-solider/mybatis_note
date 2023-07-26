package com.ppowernode.test;

import com.powernode.mapper.LogMapper;
import com.powernode.utils.SqlSessionUtil;
import org.junit.Test;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class LogMapperTest {

    @Test
    public void testLogMapper() {
        LogMapper mapper = SqlSessionUtil.openSession().getMapper(LogMapper.class);
        mapper.selectAllByTable("123456");
    }
}
