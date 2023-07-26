开发的第一个MyBatis程序

一、开发步骤
1. 更改打包方式，符合程序的要求

2. 注入依赖
    - mybatis依赖
    - mysql依赖

3. 编写mybatis的核心配置文件：`mybatis-config.xml`
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="org/mybatis/example/BlogMapper.xml"/>
    </mappers>
</configuration>
```
- 注意：
  - 这个文件名不是必须的，只是大家都会采用这种命名
    - 这个文件的存放位置也不是必须的，但一般情况下，会放到类的根目录下

4. 编写`XxxMapper.xml`SQL语句的配置文件
    ```xml
    <mapper namespace="test">
        <!-- insert语句 -->
        <!-- id 是唯一代表SQL语句的标识 -->
        <insert id="insertCar">
            insert into t_car(id, car_num, brand, guide_price, produce_time, car_type)
            values(null, '1003', '丰田霸道', 30.0, '2000-10-11', '燃油车');
        </insert>
    </mapper>
    ```
5. 在`mybatis-config.xml`文件中指定`XxxMapper.xml`的路径
    ```xml
    <mappers>
        <!-- resource属性，自动从类的根路径下寻找配置文件 -->
        <!-- 因为这里的CarMapper.xml文件，放置在了resources目录下，编译打包后，会放置到classes类目录中，所以，直接写入文件名称即可 -->
        <mapper resource="CarMapper.xml"/>
   
        <!-- url属性，从绝对路径中寻找文件，前面必须加file:/// -->
        <mapper url="file:///d:/CarMapper.xml" />
    </mappers>
   ```

6. 编写mybatis程序

二、 在`xml`中，构建：`SqlSessionFactory`

构建过程:
```java
// 获取SqlSessionFactoryBuilder对象
SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

// 获取SqlSessionFactory对象
InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

// 获取SqlSession对象，一个数据库 <==> 一个SqlSession对象
// openSession参数autoCommit设置为false <==> 开启事务，true <==> 不开启事务
// 默认false
SqlSession sqlSession = sqlSessionFactory.openSession();

// 执行sql语句
int row = sqlSession.insert("insertCar");

// ...

// 手动提交事务
sqlSession.commit();
```

三、mybatis中有两个主要的配置文件：
   - `mybatis-config.xml`：核心配置文件，主要配置连接数据库的信息。（一个）
   - `XxxMapper.xml`：专门用来编写SQL语句的配置文件。（一个表一个）
     - 例如：
       - `t_user`表 ==> `UserMapper.xml`
       - `t_student`表 ==> `StudentMapper.xml`

四、细节注意
- mybatis中，sql语句结尾的`;`可以省略
- Resources.getResourceAsStream()
  - 默认从类的根路径下寻找文件，找到后返回一个`InputStream`流对象

五、mybatis中的事务机制
可以通过：`mybatis-config.xml`文件中的`<transactionManager type="[JDBC|MANAGED]"/>`来设置
`type`中的属性包含：
- JDBC(jdbc) ==> JDBC事务管理机制
  - mybatis框架自己管理事务，自己通过采用JDBC原生的代码去管理事务
  ```java
  // 事务开启
  conn.setAutoCommit(false);
  
  sql...
  
  // 关闭事务
  conn.commit();
  ```
- MANAGED(managed) ==> MANAGED事务管理机制
  - mybatis框架将事务交给其他容器负责，比如：spring框架...
  - mybatis不在负责事务的管理

六、mybatis集成日志组件
常见：
- SLF4J（沙拉风）：是一个日志标准，其中有一个框架叫做logback，实现了SLF4J规范
- LOG4J
- LOG4J2
- STDOUT_LOGGING：mybatis内部实现的一个标准日志，不需要注入依赖，仅开启即可
  ```xml
  <settings>
        <!-- 开启mybatis对标准日志的实现 -->
        <setting name="logImpl" value="STDOUT_LOGGING"/>
  </settings>
  ```
- ...
    
    注意：LOG4J, LOG4J2, logback都是由同一个作者开发

logback
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="false">
    <!--定义⽇志⽂件的存储地址-->
    <property name="LOG_HOME" value="/home"/>
    <!-- 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示⽇期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：⽇志消息，%n是换⾏符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
    <!-- 按照每天⽣成⽇志⽂件 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--⽇志⽂件输出的⽂件名-->
            <FileNamePattern>${LOG_HOME}/TestWeb.log.%d{yyyy-MM-dd}.log</FileNamePattern>
            <!--⽇志⽂件保留天数-->
            <MaxHistory>30</MaxHistory>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示⽇期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：⽇志消息，%n是换⾏符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
        <!--⽇志⽂件最⼤的⼤⼩-->
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>100MB</MaxFileSize>
        </triggeringPolicy>
    </appender>
    <!--mybatis log configure-->
    <logger name="com.apache.ibatis" level="TRACE"/>
    <logger name="java.sql.Connection" level="DEBUG"/>
    <logger name="java.sql.Statement" level="DEBUG"/>
    <logger name="java.sql.PreparedStatement" level="DEBUG"/>
    <!-- ⽇志输出级别,logback⽇志级别包括五个：TRACE < DEBUG < INFO < WARN < ERROR -->
    <root level="DEBUG">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```