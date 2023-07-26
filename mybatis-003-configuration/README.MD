mybatis的多环境配置

在mybatis的核心系统配置文件中，有`<configuration>`根标签

其中，有一个`<environments>`标签，可以用来配置mybatis环境

```xml
<!-- 如果在创建SqlSessionFactory对象时，没有特别指定 -->
<!-- 则默认的环境为 id="mybatis" 的环境 -->
<environments default="mybatis">
    
    <!-- 一个environment标签，对应一个环境 -->
    <!-- 对应一个数据库、数据库管理系统... -->
    <environment id="mybatis">
        <transactionManager type="JDBC"/>
        <dataSource type="POOLED">
            <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
            <property name="username" value="root"/>
            <property name="password" value="wzh7230610"/>
        </dataSource>
    </environment>
    
    <environment id="bjpowernode">
        <transactionManager type="JDBC"/>
        <dataSource type="POOLED">
            <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="url" value="jdbc:mysql://localhost:3306/bjpowernode"/>
            <property name="username" value="root"/>
            <property name="password" value="wzh7230610"/>
        </dataSource>
    </environment>
</environments>
```
```java
SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(
                Resources.getResourceAsStream("mybatis-config.xml"),
                "bjpowernode");
```