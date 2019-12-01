package com.core.chapter6.chapter6_2_interfaces;

/**
 * @Author: 020188
 * @Date: 2019/11/18
 */
public class Employee implements Comparable<Employee> {

    private String name;

    private double salary;

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent){
        double raise = salary*byPercent/100;
        salary+=raise;
    }

    /**
     * 通过salary给员工排序
     * @param other
     * @return
     */
    public int compareTo(Employee other) {
        return Double.compare(salary,other.salary);
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
