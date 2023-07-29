# 动态SQL查询

- `if`标签(多条件查询)
  - 1、`if`标签中的`test`属性是必须的(required)
  - 2、`test`的值是布尔类型：`true / false` 
  - 3、为真，将`if`标签中的SQL语句拼接到外面的SQL语句中 
  - 4、`test`属性中的值
    - 当使用`@Param`注解时，`test`属性值只能填入规定的参数名
    - 当没有使用注解时，`test`属性值可以为[`param1... | arg0...`]
    - 当使用了[`bean | pojo | domain...`]包中的类，`test`属性值为类中的属性值
- `where`标签
  > 作用：让`where`子句更加动态智能
  - 所有条件都为空时，`where`标签保证不会生成`where`子句
  - 自动去除某些条件**前面**多余的`and`或`or`
  - 不能去除SQL语句后面的`and`或`or`
- `tirm`标签
  - ```xml
    <trim prefix="" prefixOverrides="" suffix="" suffixOverrides="">
        ...
    </trim>
    ```
    - `prefix`: 加前缀
    - `prefixOverrides`: 删除前缀
    - `suffix`: 加后缀
    - `suffixOverrides`: 删除后缀
- `set`标签
  > 主要使用在update语句当中，用来生成set关键字，同时去掉最后多余的“,”
  - 比如我们只更新提交的不为空的字段，如果提交的数据是空或者""，那么**这个字段**我们将不更新
  - ```xml
    <update id="updateBySet">
        update t_car
        
        <!-- 使用set标签，用于动态生成SET语句 -->
        <!-- 
            以下语句只更新属性不为null并且不为''，如果if条件不成立，则不更新此属性
            只更新条件为真的字段
        -->
        <set>
            <if test="carNum != null and carNum != ''">car_num = #{carNum},</if>
            <if test="brand != null and brand != ''">brand = #{brand},</if>
            <if test="guidePrice != null and guidePrice != ''">guide_price = #{guidePrice},</if>
            <if test="produceTime != null and produceTime != ''">produce_time = #{produceTime},</if>
            <if test="carType != null and carType != ''">car_type = #{carType},</if>
        </set>
        where
            id = #{id}
    </update>
    ```
- `choose`、`when`、`otherwise`
  > 这三个标签需要联合使用
  - ```xml
    <choose>
      <when>...</when>
      <when>...</when>
      <when>...</when>
      <otherwise>...</otherwise>
    </choose>
    ```
  - 相当与：
  - ```java
    if () {
    
    } else if () {
    
    } else if () {
    
    } else if () {
    
    } else {

    } 
    ```
- `foreach`标签
  - 属性
    - `collection`: 指定的数组或集合
    - `item`: 数组或集合中的元素
    - `separator`: 值与值之间的分隔符 ==> `delete from t_car where id in (1, 2, 3)`【分隔符为","】
    - `open`: 循环开始，以什么开始
    - `close`: 循环结束，以什么结束
- `sql`、`include`标签
  > 作用：代码复用，易于维护
  - `sql`标签: 用来指定声明SQL片段
  - `include`标签: 将声明的整个SQL片段包含到某个SQL语句中
  - ```xml
    <sql id="carCols">id,car_num carNum,brand,guide_price guidePrice,produce_time produceTime,car_type carType</sql>

    <select id="selectAllRetMap" resultType="map">
        select <include refid="carCols"/> from t_car
    </select>

    <select id="selectAllRetListMap" resultType="map">
        select <include refid="carCols"/> carType from t_car
    </select>

    <select id="selectByIdRetMap" resultType="map">
        select <include refid="carCols"/> from t_car where id = #{id}
    </select>
    ```