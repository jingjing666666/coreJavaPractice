package com.core.chapter5.chapter5_13_reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 这个程序用反射打印出类的结构所有信息
 * @Author: 020188
 * @Date: 2019/10/28
 */
public class ReflectionTest {

    public static void main(String[] args){
        String name;
        if (args.length>0){
            name=args[0];
        }else {
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter class name( e.g. java.util.Date):");
            name = in.next();
        }
        try {
            Class clazz = Class.forName(name);
            Class superClass = clazz.getSuperclass();
            String modifiers = Modifier.toString(clazz.getModifiers());
            if (modifiers.length()>0)
                System.out.println("Modifiers: "+modifiers);
            System.out.println(" Class name :"+name);
            if (superClass!=null&&superClass!=Object.class)
                System.out.println(" extends: "+superClass.getName());
            System.out.printf("{\n");
            printConstructors(clazz);
            System.out.println();
            printMethods(clazz);
            System.out.println();
            printFields(clazz);
            System.out.println();
            System.out.printf("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);

    }

    /**
     * 打印一个类的所有构造函数
     * @param clazz
     */
    public static void printConstructors(Class clazz) {
        //获取构造函数
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c: constructors) {
            //获取构造函数名称

            String name = c.getName();
            System.out.printf("  ");
            //获取构造函数修饰符
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length()>0)
                System.out.printf(" modifiers "+modifiers);
            System.out.printf(" "+name+"(");
            //输出参数类型
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i <paramTypes.length ; i++) {
                if (i>0) System.out.printf(", ");
                System.out.printf(paramTypes[i].getName());
            }
            System.out.println(" );");
        }
    }

    /**
     * 打印一个类的所有方法
     * @param clazz
     */
    private static void printMethods(Class clazz) {
       Method[] methods = clazz.getDeclaredMethods();
       for (Method method:methods){
           //方法名
           String name = method.getName();
           //方法返回值
           Class returnType = method.getReturnType();
           //获取方法修饰符
           String modifiers = Modifier.toString(method.getModifiers());
           System.out.printf(" ");
           if (modifiers.length()>0) System.out.printf(modifiers+" ");
           System.out.printf(returnType.getName()+" "+name+"(");
           //输出方法里面所有参数类型
           Class[] paramTypes = method.getParameterTypes();
           for (int i = 0; i <paramTypes.length ; i++) {
               if (i>0) System.out.printf(", ");
               System.out.printf(paramTypes[i].getName());
           }
           System.out.println(");");
       }
    }

    /**
     * 打印一个类的所有字段
     * @param clazz
     */
    private static void printFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field :fields){
            //获取域名称
            String name = field.getName();
            //获取域类型
            Class type = field.getType();
            //获取域修饰符
            String modifiers = Modifier.toString(field.getModifiers());
            System.out.printf("  ");
            if (modifiers.length()>0) System.out.printf(modifiers+" ");
            System.out.println(type.getName()+" "+name);

        }
    }


}
