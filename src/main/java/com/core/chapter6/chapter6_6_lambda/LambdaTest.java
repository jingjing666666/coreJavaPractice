package com.core.chapter6.chapter6_6_lambda;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;

/**
 * 这个程序演示lambda表达式的用法
 * lambda用简洁的语法定义代码块
 * lambda表达式与含有一个抽象方法的接口是兼容的
 * 对于只有一个抽象方法的接口，需要这种接口的对象时就可以提供lambda表达式
 * 这种接口叫函数式接口
 * Created by yuanqingjing on 2019/11/30
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = new String[]{ "Mercury" , "Venus", "Earth" , "Mars" ,
                "Jupiter" , "Saturn", "Uranus", "Neptune" };
        System.out.println(Arrays.toString(planets));
        Arrays.sort(planets);
        System.out.println("Sorted in dictionary order: ");
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in length order: ");
        //lambda表达式  （first，second）方法的参数名 ->后面的内容是方法体,
        // 如果方法体语句多用{}包着，在括号中写逻辑
        Arrays.sort(planets,(first,second)->first.length()-second.length());
        System.out.println(Arrays.toString(planets));

        //lambda表达式可以也只能转换为函数式接口
        //需要函数式接口对象的地方才能用lambda表达式
        Timer timer = new Timer(100, event->System.out.println("The time is "+new Date()) );
        timer.start();

        JOptionPane.showMessageDialog(null," Quit program?");
        System.exit(0);

    }
}
