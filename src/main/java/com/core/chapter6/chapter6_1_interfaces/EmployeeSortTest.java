package com.core.chapter6.chapter6_1_interfaces;

import com.core.chapter6.chapter6_2_interfaces.Employee;

import java.util.Arrays;

/**
 * 这个程序演示使用comparable接口对对象排序
 *
 * @Author: 020188
 * @Date: 2019/11/18
 */
public class EmployeeSortTest {

    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("jack", 75000);
        staff[1] = new Employee("lucy", 25000);
        staff[2] = new Employee("lily", 80000);
        Arrays.sort(staff);
        for (Employee employee : staff) {
            System.out.println(employee);
        }
    }
}
