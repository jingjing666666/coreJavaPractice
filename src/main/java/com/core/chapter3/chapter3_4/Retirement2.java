package com.core.chapter3.chapter3_4;

import java.util.Scanner;

/**
 * 这个程序演示do while 循环代码
 * @Author: 020188
 * @Date: 2019/10/24
 */
public class Retirement2 {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        System.out.println("How much money will you contribute");
        double payment = in.nextDouble();

        System.out.println("Interest rate in %: ");
        double interestRate = in.nextDouble();

        double balance =0;
        int years = 0;
        String input;
        do {
            balance +=payment;
            double interest = balance*interestRate/100;
            balance+=interest;
            years++;

            System.out.println(String.format("After %d ,years your balance is %.2f",years,balance));
            System.out.println("Are you Ready? Y/N");
            input=in.next();
        }while (input.equals("N"));
    }
}
