package com.core.chapter3.chapter3_2;

import java.util.Scanner;

/**
 * 这个程序演示控制台输入输出
 * @Author: 020188
 * @Date: 2019/10/24
 */
public class InputTest {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        //第一次输入
        System.out.println("What's your name?");
        String name = in.nextLine();

        //第二次输入
        System.out.println("How are you old?");
        int age = in.nextInt();

        //在控制台显示输出
        System.out.println("Your name is "+name+" and your age is "+age);

    }
}
