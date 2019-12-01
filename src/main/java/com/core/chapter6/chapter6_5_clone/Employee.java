package com.core.chapter6.chapter6_5_clone;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 实现了克隆接口的员工类
 * Created by yuanqingjing on 2019/11/30
 */
public class Employee implements Cloneable {

    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String name , double salary){
        this.name = name;
        this.salary = salary;
        hireDay = new Date();
    }

    public void setHireDay(int year,int month, int day){
        Date newHireDay = new GregorianCalendar(year,month-1,day).getTime();
        hireDay.setTime(newHireDay.getTime());
    }

    public void setName(String name){
        this.name = name;
    }

    public void raiseSalary(double byPercent){
        double raiseSalary = salary*byPercent/100;
        salary +=raiseSalary;
    }


    public Object clone() throws CloneNotSupportedException {
        //调用object的clone方法
        //调用clone方法，两个变量在栈中的地址所指向的堆对象不同
        Employee cloned = (Employee) super.clone();
        //调用Date类的clone方法
        cloned.hireDay = (Date) hireDay.clone();
        return cloned;
    }

    @Override
    public String toString() {
        return "Employee{ name = "+name+" salary: "+salary+" hireday: "+hireDay+" }";
    }
}
