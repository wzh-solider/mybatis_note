package com.powernode.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class JavassistTest {

    @Test
    public void testGenerateImpl()
            throws CannotCompileException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 获取类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = pool.makeClass("com.powernode.bank.dao.impl.AccountDaoImpl");
        // 制造接口
        CtClass ctClassImpl = pool.makeInterface("com.powernode.bank.dao.AccountDao");
        // 添加接口到类中
        ctClass.addInterface(ctClassImpl);
        // 实现接口中的方法

        // 制造方法
        CtMethod ctMethod = CtMethod.make("", ctClass);
        // 将方法添加到类中
        ctClass.addMethod(ctMethod);


        // 通过反射，实现类
        Class<?> clazz = Class.forName("com.powernode.bank.dao.impl.AccountDaoImpl");
        Object o = clazz.getConstructor().newInstance();
        Method declaredMethod = clazz.getDeclaredMethod("");
        declaredMethod.invoke(o);
    }

    @Test
    public void testGenerateFirstClass()
            throws CannotCompileException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        // 获取类池，用来生成class
        ClassPool pool = ClassPool.getDefault();
        // 制造类，告诉javassist，类名是什么
        CtClass ctClass = pool.makeClass("com.powernode.bank.dao.impl.AccountDaoImpl");
        // 制造方法
        String methodCode = "public void insert() { System.out.println(123); }";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
        // 将方法添加到类中
        ctClass.addMethod(ctMethod);
        // 在内存中生成class
        ctClass.toClass();


        // 通过反射，调用类
        Class<?> clazz = Class.forName("com.powernode.bank.dao.impl.AccountDaoImpl");
        Object o = clazz.getConstructor().newInstance();
        Method method = clazz.getDeclaredMethod("insert");
        method.invoke(o);
    }
}
