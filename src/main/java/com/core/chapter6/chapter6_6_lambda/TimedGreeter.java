package com.core.chapter6.chapter6_6_lambda;

import javax.swing.*;

/**
 * 方法引用是lambda表达式的一种
 * 方法引用也只能用在转换函数式接口的对象
 * System.out::println  ==  x->System.out.println(x)
 * 方法引用用::分隔方法名与对象或者类名
 * 方法引用的方式
 * 1.object::instanceMethod
 * 2.Class::instanceMethod
 * 3.Class::StaticMethod
 *
 * 1 3  System.out::println  ==  x->System,out.println(x) 类似Math::pow  ==  (x,y)->Math.pow(x,y)
 * 2 对于第二种情况  第一个参数会成为目标 （x,y）->x.compareToIgnore(y)  == String::compareToIgnore
 *
 * 构造器引用用法
 * Person::new 是对person构造器的一个引用,用法如下
 * Person[] persons = stream.toArray(Person::new)
 * toArray调用这个构造器来得到正确类型的数组，然后填充这个数组并返回
 *
 * 变量作用域
 * lambda表达式中有三个部分
 * 1.代码块
 * 2.参数
 * 3.自由变量，这里指非参数并且不在代码块中定义的变量
 * 在lambda表达式中只能引用值不会改变的自由变量
 * 在方法中不能有两个同名的局部变量，在lambda表达式中也不能有两个同名的局部变量
 * lambda表达式中的this，外部方法的this是同一个
 *
 * 处理lambda表达式
 * 使用lambda表达式的重点是延迟执行
 * 希望以后在执行代码的原因：
 * 1.在一个单独的线程中运行代码
 * 2.多次运行代码
 * 3.在算法的适当位置运行代码（例如排序中的比较操作）
 * 4.发生某种情况时执行代码（如点击了一个按钮，数据到达等等）
 * 5.只在必要时才运行代码
 *
 * 例：想要一个动作重复n次
 * repeat(10,()->System.out.println("hello"));
 *
 * void repeat( int count, Runnable action){
 *     for(int i = 0;i<count;i++)
 *     action.run();
 * }
 *调用action.run会执行lambda表达式的主体
 *
 * 复杂一点lambda表达式的例子
 * 首先定义一个函数式接口
 *
 * public void IntConsumer {
 *     void accept(int value);
 * }
 *
 * repeat(10,x->System.out.println("Count_down "+(9-x)))
 *
 * void repeat(int count ,IntConsumer consumer){
 *     for(int i=0;i<count ;i++){
 *     consumer.accept(i);
 * }
 *
 * 再谈Comparator
 * Comparator接口提供了很多静态的方法来创建比较器，这些方法可用于lambda表达式或方法引用
 * 1.静态comparing方法取一个键提取器函数，它将类型T映射为一个可比较的类型（如String）
 * 例：Person对象数组按名字排序
 * Arrays.sort(person,Comparator.comparing(Person::getName))
 * 代码更加清晰
 * 先根据对象的姓排序再根据对象的名排序
 * Arrays.sort(person,Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName))
 * Created by yuanqingjing on 2019/11/30
 */
public class TimedGreeter extends Greeter{


        public void greet() {
            Timer timer = new Timer(100,super::greet);
            timer.start();
            JOptionPane.showMessageDialog(null," Quit program?");
            System.exit(0);
        }

    public static void main(String[] args) {
        TimedGreeter greeter = new TimedGreeter();
        greeter.greet();
    }

}
