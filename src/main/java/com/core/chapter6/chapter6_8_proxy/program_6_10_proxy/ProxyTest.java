package com.core.chapter6.chapter6_8_proxy.program_6_10_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 这个程序演示代理的用法
 * Created by yuanqingjing on 2019/12/2
 */
public class ProxyTest {

    public static void main(String[] args) {
        Object[] elements = new Object[10];
        for (int i=0;i<elements.length;i++){
            Integer integer = i+1;
            InvocationHandler handler = new TraceHandle(integer);
            //代理对象proxy属于运行时定义的类$Proxy0,Integer实现了Comparable接口
            Object proxy = Proxy.newProxyInstance(null,new Class[]{Comparable.class},handler);
            //无论何时用proxy调用某个方法，invoke方法会被调用，打印方法名和参数
            elements[i] = proxy;
        }

        int key = new Random().nextInt(elements.length)+1;
        int result = Arrays.binarySearch(elements,key);
        //toString 方法也被代理
        if (result>0) System.out.println(elements[result]);


    }
}

/**
 * 调用处理器
 */
class TraceHandle implements InvocationHandler{

    private Object target;

    /**
     * 构造方法
     * @param t 回调方法的隐形参数
     */
    public TraceHandle(Object t){
        target = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //隐形参数
        System.out.print(target);
        //输出方法名
        System.out.print("."+method.getName()+"(");
        //输出明确参数
        if (args!=null){
            for (int i=0;i<args.length;i++){
                System.out.print(args[i]);
                if (i<args.length-1) System.out.print(",");
            }
        }
        System.out.println(")");
        //实际方法调用
        return method.invoke(target,args);
    }
}