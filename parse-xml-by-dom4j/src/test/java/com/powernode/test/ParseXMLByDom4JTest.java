package com.powernode.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.AbstractDocument;
import org.junit.Test;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class ParseXMLByDom4JTest {
    @Test
    public void testParseMyBatisConfigXML() throws DocumentException {
        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 获取流对象
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        // 根据流对象，获取文档对象
        Document document = reader.read(stream);
        // 获取根节点
//        Element rootElement = document.getRootElement();
//        String name = rootElement.getName();
//        System.out.println(name);

        // 使用xpath快速获取路径下的对象
        String xpath = "/configuration/environments";
        Element environments = (Element) document.selectSingleNode(xpath);
        String s = environments.attributeValue("default");
        xpath = "/configuration/environments/environment[@id='" + s + "']";
        Element environment = (Element) document.selectSingleNode(xpath);

        // 根据指定id的environment获取到子节点
        Element transactionManager = environment.element("transactionManager");
        String transactionType = transactionManager.attributeValue("type");
        System.out.println("事务类型 = " + transactionType);

        // 获取数据源的类型
        Element dataSource = environment.element("dataSource");
        String dataSourceType = dataSource.attributeValue("type");
        System.out.println("数据源的类型 = " + dataSourceType);

        // 获取dataSource下所有的子节点
        List<Element> properties = dataSource.elements();
        properties.forEach(property -> {
            String name = property.attributeValue("name");
            String value = property.attributeValue("value");
            System.out.println(name + " : " + value);
        });

        // 获取所有的Mapper标签
        xpath = "//mapper";
        List<Node> mappers = document.selectNodes(xpath);
        mappers.forEach(mapper -> {
            Element mapperElt = (Element) mapper;
            String resource = mapperElt.attributeValue("resource");
            System.out.println(resource);
        });
    }

    @Test
    public void testParseCarMapperXML() throws DocumentException {
        
        SAXReader saxReader = new SAXReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");
        Document document = saxReader.read(is);

        String xpath = "/mapper";
        Element mapper = (Element) document.selectSingleNode(xpath);

        List<Element> sqls = mapper.elements();
        sqls.forEach(sql -> {
            String id = sql.attributeValue("id");
            String resultType = sql.attributeValue("resultType");
            String s = sql.getTextTrim();
            System.out.println(id);
            System.out.println(resultType);
            System.out.println("sql语句：" + s);
        });
    }
}
