使用mybatis对mysql数据库进行CRUD(增、查、改、删)操作

当我们使用配置文件进行映射，书写SQL语句的时候，不建议直接给定固定值

例如：
```xml
<insert id="insertCar">
    insert into t_car(id, car_num, brand, guide_price, produce_time, car_type)
    values(null, '1003', '丰田霸道', 30.0, '2000-10-11', '燃油车');
</insert>
```
这样写，会降低程序的扩展性，不利于后期维护

当使用JDBC编程时，会使用占位符`?`来对值进行传递

所以，在mybatis中，我们可以使用`#{}`来发挥和JDBC中`?`占位符相同的作用

- 占位符传值
  - 在$Java$代码中，可以使用$Map$集合，来实现对SQL语句的传值
  ```java
  Map<String, Object> map = new HashMap<>();
  map.put("car_num", "001");
  map.put("brand", "baoma");
  map.put("guide_price", 100.0);
  map.put("produce_time", "2023-10-20");
  map.put("car_type", "新能源汽车");
  
  // 获取SqlSession对象
  // ...
  
  int count = sqlSession.insert("insertCar", map);
  
  // ...
  ```
  ```xml
  <insert id="insertCar">
      insert into t_car(id, car_num, brand, guide_price, produce_time, car_type)
      values(null, #{car_num}, #{brand}, #{guide_price}, #{produce_time}, #{car_type});
  </insert>
  ```
  - 在`#{}`的花括号中填入传入$Map$的键

在$Java$程序中，使用[bean|domain|pojo]类给SQL语句传值

![img.png](img.png)
```java
Car car = new Car(null, "2222", "haahah", 12.22, "2003-02-10", "消防车");
```
```xml

<insert id="insertCar">
    insert into t_car(id, car_num, brand, guide_price, produce_time, car_type)
    values(null, #{carNum}, #{brand}, #{guidePrice}, #{produceTime}, #{carType});
</insert>
```
- 在`#{}`的花括号中填入，`xxx`，底层会调用`xxx`的`getXxx`方法
- 也可以使用`${}`的方法进行传值
  - `#{}`和`${}`的区别
    - `#{}` ==> `PrepareStatement`，可以预防SQL注入
    - `${}` ==> `Statement`，有SQL注入的风险
  - 怎样选择？
    - 当需要考虑使用升序 / 降序等，必须使用`${}`，而不能使用`#{}`
      - `#{}`会将值传参，从而将传入的参数变为字符串类型`''`，导致`select * from table order by 'desc'`SQL语句出错
    - 当需要考虑字符串拼接时，必须使用`${}`，而不是`#{}`

MyBatis查询语句的使用

```xml
<select id="selectOneCar" resultType="com.powernode.mybatis.bean.Car">
    select
    id,
    car_num as carNum,
    brand,
    guide_price as guidePrice,
    produce_time as produceTime,
    car_type as carType
    from t_car where id = #{id}
</select>
```
- 当只查询一条语句，需要向mybatis告知返回的对象类型
  - `resultType`属性中添加需要映射的类的 '全限定类名'
  - 同时，在查询的SQL语句中，需要为列 起别名，而这个别名 <==> 需要和映射类的对应名相同
  - 否则，查询结果会为 `null`

映射配置文件中的会有一个属性 ==> `namespace`

```xml
<!-- 这里的namespace是唯一标识mapper的映射文件的 -->
<mapper namespace="userMapper">...</mapper>
```
- 如果在一个mybatis项目中，有多个mapper文件，而且其中SQL语句的id属性有同名的
- 那么在$Java$代码中，此时根据id直接寻找，就会报错
- 解决方法，就是在id值前面加上namespace属性
- 例如：
```xml
<mapper namespace="userMapper">
    <select id="selectAll" resultType="com.powernode.mybatis.bean.Car">
        select id,
        car_num as carNum,
        brand,
        guide_price as guidePrice,
        produce_time as produceTime,
        car_type as carType
        from t_car
    </select>
</mapper>
```
```java
List<Object> obj= sqlSession.selectList("userMapper.selectAll");
```