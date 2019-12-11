package com.core.chapter7.chapter7_1_exception.program7_1_stackTrace;

import java.util.Scanner;

/**
 * 打印递归阶乘函数的堆栈情况
 * Created by yuanqingjing on 2019/12/11
 */
public class StackTraceTest {
    /**
     * 计算一个数的阶乘
     * @param n
     * @return
     */
    public static int factorial(int n){
        System.out.println("factorial "+"( "+n+" )");
        Throwable throwable = new Throwable();
        StackTraceElement[] elements = throwable.getStackTrace();
        for (StackTraceElement e:elements) {
            //StackTraceElement的toString方法，返回类名、方法名、文件名、行数
            System.out.println(e);
        }
        int r;
        if (n<=1) r = 1;
        else
        r = n*factorial(n-1);
        System.out.println(" return: "+r);
        return r;

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please Enter number");
        int n = scanner.nextInt();
        factorial(n);
    }
}
