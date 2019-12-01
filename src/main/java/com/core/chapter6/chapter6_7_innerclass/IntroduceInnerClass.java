package com.core.chapter6.chapter6_7_innerclass;

/**
 * 内部类：定义在另一个类中的类
 * 需要内部类的原因：
 * 1.内部类方法可以访问该类定义所在作用域的数据，包括私有数据
 * 2.内部类可以对同一个包中的其它类隐藏
 * 3.当想要定义一个回调函数并不想编写大量代码时，可以使用匿名内部类
 *
 * 使用内部类访问对象状态
 * 1.内部类的使用方式，例子如下：
 * public class TalkingClock{
 *     private int interval;
 *     private boolean beep;
 *
 *     public TalkingClock(int interval,boolean beep){
 *         ......
 *     }
 *
 *     public void start(){
 *         ....
 *     }
 *      //TimerPrinter是一个内部类
 *     public class TimerPrinter implements ActionListener{
 *         public void actionPerformed( ActionEvent e){
 *              System.out.println("The Timer is "+new Date());
 *              //引用外部类的变量，内部类既可以访问自身的数据域，
 *              也可以访问创建他的外围类对象的数据域
 *              if(beep){
 *                  Toolkit.getDefaultToolKit.beep();
 *              }
 *         }
 *     }
 *     //内部类对象总有一个隐式引用指向外部类的对象
 * }
 *
 *
 * 内部类的特殊语法规则
 * 使用外围类数据的正规语法  TalkingClock.this.beef   OuterClass.this
 *
 * 外部类也可以创建内部类的对象
 * outObject.new InnerClass（construction parameter）
 * ActionListener timePrinter = this.new TimePrinter();
 * 在外围类的作用域之外这样引用内部类
 * OuterClass.InnerClass
 * Created by yuanqingjing on 2019/12/1
 */
public class IntroduceInnerClass {
}
