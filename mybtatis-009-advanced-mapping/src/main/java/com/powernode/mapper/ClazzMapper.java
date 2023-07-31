package com.powernode.mapper;

import com.powernode.bean.Clazz;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface ClazzMapper {

    /**
     * 分步查询第一步
     */
    Clazz selectByIdStep1(Integer cid);

    /**
     * 通过collection进行查询
     * @param cid 传入班级的cid
     * @return Clazz
     */
    Clazz selectByCollection(Integer cid);

    /**
     * 分步查询第二步
     * @param cid 传入班级的cid
     * @return Clazz
     */
    Clazz selectByIdStep2(Integer cid);
}
