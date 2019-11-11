package com.core.chapter5.chapter5_17_methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 这个程序展示怎么通过反射调用方法
 * @Author: 020188
 * @Date: 2019/11/7
 */
public class MethodTableTest {

    public static void main(String[] args){
        try {
            Method square = MethodTableTest.class.getMethod("square", double.class);
            Method sqrt = Math.class.getMethod("sqrt", double.class);
            printTable(1,10,10,square);
            printTable(1,10,10,sqrt);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    /**
     * 返回一个数的平方
     * @param x
     * @return
     */
    public static double square(double x){
        return x*x;
    }

    /**
     * 打印包含方法的x和y值的表
     * @param from x的最小边界值
     * @param to x的最大边界值
     * @param n 表的行数
     * @param f 有参数和double返回值的方法
     */
    public static void printTable(double from, double to , int n , Method f){
        double dx = (to-from)/(n-1);
        for (double x= from ;x<=to;x+=dx){
            try {
                double y = (Double) f.invoke(null,x);
                System.out.printf("%10.4f | %10.4f%n",x,y);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }
}
