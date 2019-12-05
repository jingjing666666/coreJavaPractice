package com.core.chapter6.chapter6_7_innerclass;

/**
 * 内部类：定义在另一个类中的类
 * 需要内部类的原因：
 * 1.内部类方法可以访问该类定义所在作用域的数据，包括私有数据
 * 2.内部类可以对同一个包中的其它类隐藏
 * 3.当想要定义一个回调函数并不想编写大量代码时，可以使用匿名内部类
 *
 * 一.使用内部类访问对象状态
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
 * 二.内部类的特殊语法规则
 * 使用外围类数据的正规语法  TalkingClock.this.beef   OuterClass.this
 *
 * 外部类也可以创建内部类的对象
 * outObject.new InnerClass（construction parameter）
 * ActionListener timePrinter = this.new TimePrinter();
 * 在外围类的作用域之外这样引用内部类
 * OuterClass.InnerClass
 * TalkingClock clock = new TalkingClock(100,false);
 * TalkingClock.TimerPrinter timer = clock.new TimerPrinter();
 * 内部类的所有静态变量都必须是final ，因为static域只有一个实例，
 * 每一个外部对象都单独有一个内部类实例，如果域不是final，它可能就不是唯一的
 * 内部类的静态方法只能访问外围类的静态域和方法
 *
 * 三.内部类是否有用必要和安全
 * 内部类是一种编译器现象与虚拟机无关
 * 编译器以美元符号分隔外围类和内部类命名class文件
 *
 * 编译器给TimerPrinter类添加了一个构造方法和final域,这个this就是指向外围类
 * public TimePrinter（TalkingClock）;
 * final TalkingClock this$0;
 * 内部类能够访问外部类的私有数据，内部类拥有这种访问特权，比一般类功能更加强大
 * 为什么内部类能够访问外围类的私有数据，编译器在外围类添加静态方法access$000
 * if(beep)
 * if(TalkingClocking.access$000(outer))
 *
 * 四.局部内部类
 * 由于TimePrinter类只在TalkingClock类的start的方法中创建类型的对象用过一次，
 * 这种情况可以在方法中定义一个局部类
 * public void start(){
 *     class TimePrinter implements ActionListener{
 *
 *         public void actionPerformed(ActionEvent e){
 *             System.out.println(" At the stone , the time is "+new Date());
 *             if(beef){
 *                 ToolKit.getDefaultToolKit().beep();
 *             }
 *         }
 *     }
 *     ActionListener listener = new TimePrinter();
 *     Timer timer = new Timer(100,listener);
 *     t.start();
 * }
 * 局部类不能用public private 修饰，它的作用域被限定在声明这个局部类的块中
 * 局部类有一个优势，对外部世界完全隐藏，TalkingClock类中的其他方法不能访问它，
 * 除start方法之外，没有任何方法知道TimePrinter类的存在。
 *
 * 五.由外部方法访问变量
 * 局部类还有一个优点，他不仅可以访问包含他们的外部类，还可以访问局部变量，
 * 不过局部变量必须为final
 * 示例：
 * public void start(int interval ,boolean beep){
 *     class TimePrinter implements ActionListener{
 *         public void start(){
 *             System.out.println("At the stone, the time is "+new Date());
 *             if(beep){
 *                 Toolkit.getDefaultToolkit().beep();
 *             }
 *         }
 *     }
 *     ActionListener listener = new TimePrinter();
 *     Timer timer = new Timer(100,listener);
 *     timer.start();
 * }
 * 编译器给TimePrinter添加了 final 类型的beef变量和TalkingClock,boolean类型的构造方法
 * public TimePrinter（TalkingClock,boolean）;
 * final boolean val$beep;
 * 所以局部类访问的局部变量必须是final类型也只能访问final类型的变量，否则就会报错
 *
 * 六.匿名内部类
 * 如果将局部内部类的使用再深入一步，只创建这个类的一个对象，就不必命名了。
 * 这种类被称为匿名内部类 anonymous inner class,例
 * public void start(int interval ,boolean beep){
 *     ActionListener listener = new ActionListener(){
 *
 *         public void actionPerformed(ActionEvent event){
 *             System.out.println("At the stone, the time is "+ new Date());
 *             if(beep){
 *                 Toolkit.getDefaultToolkit().beep();
 *             }
 *         }
 *     }
 *     Timer timer = new Timer (100,listener);
 *     timer.start();
 * }
 * //创建一个实现ActionListener接口的新对象，需要实现的方法定义在actionPerformed{}中
 * 通常的语法格式为
 * new SuperType(Constructor parameters){
 *     inner class methods and data
 * }
 * SuperType可以是超类也可以是接口，如果是接口就需要实现方法，如果是超类就需要扩展它
 * 由于构造器的名字必须与类名相同，但是匿名内部类没有类名，所以匿名类不能有构造器，将
 * 构造器参数传给超类构造器
 * 在匿名内部类实现接口时不能有任何构造参数还要提供一组（）
 * new InterfaceType(){
 *
 * }
 *
 * public void start(int interval ,boolean beep){
 *     Timer timer = new Timer(100,event->{
 *
 *         System.out.println("At the stone,the time is "+new Date());
 *         if(beep){
 *             Toolkit.getDefaultToolkit().beep();
 *         }
 *     });
 *     timer.start();
 * }
 * 七.静态内部类
 * 使用内部类只需要把一个类隐藏在另一个类的内部，并不需要内部类引用外部类对象，
 * 为此，可以将这个类设置为static，取消产生的引用
 * 使用静态内部类的典型例子，计算数组中最小值和最大值的例子：
 *
 * Created by yuanqingjing on 2019/12/1
 */
public class IntroduceInnerClass {
}
