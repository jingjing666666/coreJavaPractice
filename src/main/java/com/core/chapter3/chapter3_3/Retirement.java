package com.core.chapter3.chapter3_3;

import java.util.Scanner;

/**
 * 这个程序演示while循环
 * @Author: 020188
 * @Date: 2019/10/24
 */
public class Retirement {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.println("How much money do you need to retire?");
        Double goal = in.nextDouble();

        System.out.println("How much money will you contribute every year?");
        Double payment = in.nextDouble();

        System.out.println("Interest rate in %:");
        int interestRate = in.nextInt();

        Double balance= new Double(0);
        int years = 0;
        while (balance<goal){
            balance+=payment;
            double interest =balance*interestRate/100;
            balance+=interest;
            years++;
        }

        System.out.println("You need to "+years+" years to retire");

    }

}
