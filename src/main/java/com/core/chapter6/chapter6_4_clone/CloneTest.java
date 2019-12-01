package com.core.chapter6.chapter6_4_clone;

import com.core.chapter6.chapter6_5_clone.Employee;

/**
 * 对象的深克隆和浅克隆
 * 这个程序主要展示克隆clone，clone是浅克隆如果有引用类型需要重写
 * Created by yuanqingjing on 2019/11/27
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee employee =  new Employee("lily",1000);
        /**
         *  a = b.clone()调用clone方法，栈地址所指向的堆对象不同
         *  a = b 如果是赋值，栈地址所指向的堆对象相同
         *  clone()只是把类中所有属性拷贝过来，如果是基本数据类型，
         *  不会影响原类型的属性值，如果属性里面有引用类型，则会影响
         *  原类型的属性值，需要重写clone方法，在clone方法中再次调用属
         *  性为引用类型的clone方法，直到属性中再也没有引用类型，否则clone仍然是浅拷贝
         */
        Employee cloned = (Employee) employee.clone();
        cloned.raiseSalary(10);
        cloned.setHireDay(2019,11,30);
        cloned.setName("Rose");
        System.out.println("original: "+employee);
        System.out.println("cloned: "+cloned);
    }
}
