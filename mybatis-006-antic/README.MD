# 使用mybatis的一些小技巧

- 删除多条数据
  - `select * from t_car where id in (${ids})`
  - `select * from t_car where id = 1 or id = 2 ...`
- 模糊查询
  - `select * from t_car where brand like '%${brand}%'`
  - `select * from t_car where color like "%"#{brand}"%"`
  - `select * from t_car where brand like concat('%', #{brand}, '%')`
- MyBatis别名机制
```xml
    <!--
        <typeAliases>标签，用于给指定路径，起别名
        type="类或类目标名称"
        alias="别名" ==> 不区分大小写。
    -->
    <!-- alias属性，可以省略，默认为全限定类名的简写
        Eg: type="com.powernode.bean.Car" ==> 默认alias="car / Car..."不区分大小写。 -->
    
    <!-- 如果需要多个别名，可以使用package标签，配置多个别名目录 
        Eg: <package name="com.powernode.bean"/> mybatis会将此包下的所有类，起别名，默认简名-->
    <typeAliases>
        <!-- <typeAlias type="com.powernode.bean.Car" alias="car"/> -->
        <package name="com.powernode.bean"/>
    </typeAliases>
```