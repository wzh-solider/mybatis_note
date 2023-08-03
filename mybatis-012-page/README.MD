# 使用PageHelper插件，进行分页
## 1、引入依赖
```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.3.3</version>
</dependency>
```
## 2、在mybatis核心配置文件中配置
```xml
<!-- mybatis分页拦截器 -->
<plugins>
    <plugin interceptor="com.github.pagehelper.PageInterceptor"/>
</plugins>
```