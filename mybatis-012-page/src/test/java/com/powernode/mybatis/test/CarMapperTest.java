package com.powernode.mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.powernode.mybatis.bean.Car;
import com.powernode.mybatis.mapper.CarMapper;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CarMapperTest {

    @Test
    public void testSelectAll() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);

        // 当在执行DQL语句中，需要开启分页查询功能
        int pageNum = 1;
        int pageSize = 2;

        PageHelper.startPage(pageNum, pageSize);

        List<Car> cars = mapper.selectAll();

        PageInfo<Car> pageInfo = new PageInfo<>(cars, 5);
        /**
         * pageInfo返回的信息
         * PageInfo{pageNum=1, pageSize=2, size=2, startRow=1, endRow=2, total=4, pages=2,
         * list=Page{count=true, pageNum=1, pageSize=2, startRow=0, endRow=2, total=4, pages=2, reasonable=false, pageSizeZero=false}
         * [Car{id=6, carNum='1000', brand='abd', guidePrice=200.00, produceTime='2002-12-20', carType='新能源'},
         *  Car{id=7, carNum='1000', brand='abd', guidePrice=200.00, produceTime='2002-12-20', carType='新能源'}],
         *  prePage=0, nextPage=2, isFirstPage=true, isLastPage=false, hasPreviousPage=false, hasNextPage=true,
         *  navigatePages=5, navigateFirstPage=1, navigateLastPage=2, navigatepageNums=[1, 2]}
         */
        System.out.println(pageInfo);

        // cars.forEach(System.out::println);
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectByPage() {

        // 设置每页的记录条数
        int pageSize = 2;
        // 设置页数
        int pageNum = 2;
        // 计算下标
        int startIndex = (pageNum - 1) * pageSize;

        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByPage(startIndex, pageSize);
        cars.forEach(System.out::println);
        SqlSessionUtil.close();
    }
}
